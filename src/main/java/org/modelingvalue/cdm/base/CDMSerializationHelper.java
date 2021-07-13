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

import java.util.Objects;
import java.util.function.Predicate;

import org.modelingvalue.collections.List;
import org.modelingvalue.collections.Set;
import org.modelingvalue.dclare.Mutable;
import org.modelingvalue.dclare.Setable;
import org.modelingvalue.dclare.sync.SerializationHelper;

public class CDMSerializationHelper implements SerializationHelper<CDMClass<CDMObject>, CDMObject, Setable<CDMObject, Object>> {
    public static final String             SEPARATOR = "-";
    private final       CDMStoreAndFactory factory;

    public CDMSerializationHelper(CDMStoreAndFactory factory) {
        this.factory = factory;
    }

    @Override
    public Predicate<Mutable> mutableFilter() {
        return mutable -> {
            boolean b = true;
            System.err.println("[CDMService] mutableFilter       " + (b ? "accept" : "reject") + " " + mutable + " (" + mutable.getClass() + ")");
            return b;
        };
    }

    @Override
    public Predicate<Setable<CDMObject, ?>> setableFilter() {
        return settable -> {
            boolean b = settable != null && settable.id() instanceof String && ((String) settable.id()).startsWith("=");
            System.err.println("[CDMService] setableFilter       " + (b ? "accept" : "reject") + " " + settable + (settable == null ? "" : " (" + settable.getClass() + ")"));
            return b;
        };
    }

    @Override
    public String serializeClass(CDMClass<CDMObject> clazz) {
        System.err.println("[CDMService] serializeClass      " + clazz + " => null");
        return null;
    }

    @Override
    public String serializeSetable(Setable<CDMObject, Object> setable) {
        System.err.println("[CDMService] serializeSetable    => " + setable);
        return setable.toString().substring(1);
    }

    @Override
    public String serializeMutable(CDMObject mutable) {
        String result = mutable.getClass().getSimpleName() + SEPARATOR + mutable.getId();
        System.err.println("[CDMService] serializeMutable    " + mutable + " => " + result);
        return result;
    }

    @Override
    public Object serializeValue(Setable<CDMObject, Object> setable, Object value) {
        Object result = value;
        if (value == null) {
            System.err.printf("[CDMService] serializeValue      %-25s null    value: %s\n", setable, result);
        } else if (value instanceof Integer) {
            System.err.printf("[CDMService] serializeValue      %-25s Integer value: %s\n", setable, result);
        } else if (value instanceof String) {
            System.err.printf("[CDMService] serializeValue      %-25s String  value: %s\n", setable, result);
        } else if (value instanceof Boolean) {
            System.err.printf("[CDMService] serializeValue      %-25s Boolean value: %s\n", setable, result);
        } else if (value instanceof List) {
            result = ((List<?>) value).map(e -> serializeValue(setable, e)).toList();
            System.err.printf("[CDMService] serializeValue      %-25s List    value: %s\n", setable, result);
        } else if (value instanceof CDMObject) {
            result = serializeMutable((CDMObject) value);
            System.err.printf("[CDMService] serializeValue      %-25s List    value: %s\n", setable, result);
        } else {
            result = null;
            System.err.printf("[CDMService] serializeValue      %-25s IGNORED VALUE: %s\n", setable, result);
        }
        return result;
    }

    @Override
    public CDMClass<CDMObject> deserializeClass(String s) {
        System.err.println("[CDMService] deserializeClass    (" + s + ") => null");
        return null;
    }

    @Override
    public Setable<CDMObject, Object> deserializeSetable(CDMClass<CDMObject> clazz, String s) {
        Set<? extends CDMProperty<CDMObject, ?>> properties  = clazz.properties();
        String                                   propName    = "=" + s;
        CDMProperty<CDMObject, ?>                cdmProperty = properties.filter(p -> p.getName().equals(propName)).findAny().orElse(null);
        if (cdmProperty == null) {
            throw new IllegalArgumentException("can not deserializeSetable '" + s + "', '" + propName + "' is not a property of " + clazz);
        }
        Setable<CDMObject, ?> setable = cdmProperty.setable();
        System.err.println("[CDMService] deserializeSetable  (clazz=" + clazz + ", s=" + s + ") => " + setable);
        //noinspection unchecked
        return (Setable<CDMObject, Object>) Objects.requireNonNull(setable);
    }

    @Override
    public CDMObject deserializeMutable(String s) {
        String[]  typeWithId = s.split(SEPARATOR);
        CDMObject mutable    = factory.findOrCreateInstance(typeWithId[0], typeWithId[1]);
        System.err.println("[CDMService] deserializeMutable  (" + s + ") => " + mutable);
        return Objects.requireNonNull(mutable);
    }

    @Override
    public Object deserializeValue(Setable<CDMObject, Object> setable, Object s) {
        Object result = s;
        if (s instanceof List) {
            result = ((List<?>) s).map(e -> deserializeMutable(e.toString())).toList();
            System.err.printf("[CDMService] deserializeValue    (%-25s, %-30s) => (list)         %s\n", setable, s, result);
        } else if (s instanceof String && ((String) s).contains(SEPARATOR)) {
            result = deserializeMutable((String) s);
            System.err.printf("[CDMService] deserializeValue    (%-25s, %-30s) => (CDMObject)    %s\n", setable, s, result);
        } else if (s instanceof String) {
            System.err.printf("[CDMService] deserializeValue    (%-25s, %-30s) => (CDMObject)    %s\n", setable, s, result);
        } else if (s instanceof Long) {
            result = ((Long) s).intValue();
            System.err.printf("[CDMService] deserializeValue    (%-25s, %-30s) => (int)          %s\n", setable, s, result);
        } else if (s instanceof Boolean) {
            System.err.printf("[CDMService] deserializeValue    (%-25s, %-30s) => (boolean)      %s\n", setable, s, result);
        } else {
            throw new IllegalArgumentException("can not deserializeValue " + s + " as " + setable);
        }
        return result;
    }
}
