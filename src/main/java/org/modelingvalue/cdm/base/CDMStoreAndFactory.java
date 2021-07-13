//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// (C) Copyright 2018-2021 Modeling Value Group B.V. (http://modelingvalue.org)                                        ~
//                                                                                                                     ~
// Licensed under the GNU Lesser General Public License v3.0 (the 'License'). You may not use this file except in      ~
// compliance with the License. You may obtain a copy of the License at: https://choosealicense.com/licenses/lgpl-3.0  ~
// Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on ~
// an 'AS IS' BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the  ~
// specific language governing permissions and limitations under the License.                                          ~
//                                                                                                                     ~
// Maintainers:                                                                                                        ~
//     Wim Bast, Tom Brus, Ronald Krijgsheld                                                                           ~
// Contributors:                                                                                                       ~
//     Arjan Kok, Carel Bast                                                                                           ~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

package org.modelingvalue.cdm.base;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CDMStoreAndFactory {
    private final Map<String, FactoryDepartment> departmentMap;
    private final FactoryDepartment              universeDepartment;
    private final Map<String, CDMObject>         store = new HashMap<>();

    @SafeVarargs
    protected CDMStoreAndFactory(Class<? extends CDMObject>... modelClasses) {
        this.departmentMap = FactoryDepartment.of(modelClasses);
        List<FactoryDepartment> universeDepartments = departmentMap.values().stream().filter(FactoryDepartment::isUniverseDepartment).collect(Collectors.toList());
        if (universeDepartments.isEmpty()) {
            throw new IllegalArgumentException("CDMStoreAndFactory requires one Universe type class, none found");
        }
        if (1 < universeDepartments.size()) {
            throw new IllegalArgumentException("CDMStoreAndFactory requires one Universe type class, multiple found");
        }
        universeDepartment = universeDepartments.get(0);
    }

    public CDMObject findOrCreateInstance(String type, String id) {
        CDMObject instance = findInstance(type, id);
        if (instance == null) {
            synchronized (this) {
                instance = store.computeIfAbsent(makeKey(type, id), __ -> createInstance(type, id));
            }
        }
        return instance;
    }

    public CDMObject findInstance(String type, String id) {
        if (type.equals(universeDepartment.getTypeId())) {
            return universeDepartment.getLastCreated();
        } else {
            return store.get(makeKey(type, id));
        }
    }

    public CDMObject createInstance(String type, String id) {
        FactoryDepartment dep = departmentMap.get(type);
        if (dep == null) {
            throw new IllegalArgumentException("can not create a '" + type + "' with id '" + id + "'");
        }
        return dep.createInstance(id);
    }

    private String makeKey(String type, String id) {
        return type + "-" + id;
    }

    public static String getTypeId(Class<? extends CDMObject> clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

    private static class FactoryDepartment {
        public static Map<String, FactoryDepartment> of(Class<? extends CDMObject>[] classes) {
            return Arrays.stream(classes).map(FactoryDepartment::new).collect(Collectors.toMap(FactoryDepartment::getTypeId, fd -> fd));
        }

        private final Class<? extends CDMObject>       clazz;
        private final Constructor<? extends CDMObject> constructor;
        private       CDMObject                        lastCreated;

        public FactoryDepartment(Class<? extends CDMObject> clazz) {
            this.clazz = clazz;
            try {
                constructor = clazz.getConstructor(Object.class);
            } catch (NoSuchMethodException e) {
                throw new IllegalArgumentException("no constructor(Object) found in " + getTypeId(), e);
            }
        }

        private String getTypeId() {
            return CDMStoreAndFactory.getTypeId(clazz);
        }

        public CDMObject createInstance(String id) {
            try {
                lastCreated = constructor.newInstance(id);
                return lastCreated;
            } catch (Exception e) {
                throw new IllegalArgumentException("could not create instance of " + getTypeId(), e);
            }
        }

        public boolean isUniverseDepartment() {
            return CDMUniverse.class.isAssignableFrom(clazz);
        }

        public CDMObject getLastCreated() {
            return lastCreated;
        }
    }
}
