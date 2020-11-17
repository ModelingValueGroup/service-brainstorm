//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// (C) Copyright 2018-2019 Modeling Value Group B.V. (http://modelingvalue.org)                                        ~
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

package base;

import org.modelingvalue.collections.Set;
import org.modelingvalue.dclare.MutableClass;
import org.modelingvalue.dclare.Observer;
import org.modelingvalue.dclare.Setable;

public class DClass<C extends DObject> implements MutableClass {

    private final Class<C>                     cls;
    private final Set<DProperty<C, ?>>         properties;
    private final Set<? extends Setable<C, ?>> setables;
    private final Set<? extends Observer<C>>   observers;

    public static <T extends DObject> DClass<T> of(Class<T> cls, Set<DProperty<T, ?>> properties) {
        return new DClass<T>(cls, properties);
    }

    private DClass(Class<C> cls, Set<DProperty<C, ?>> properties) {
        this.cls = cls;
        this.properties = properties;
        this.setables = properties.map(DProperty::setable).toSet();
        this.observers = properties.map(DProperty::observer).notNull().toSet();
    }

    @Override
    public Set<? extends Observer<C>> dObservers() {
        return observers;
    }

    @Override
    public Set<? extends Setable<C, ?>> dSetables() {
        return setables;
    }

    public Set<DProperty<C, ?>> properties() {
        return properties;
    }

    public Class<C> cls() {
        return cls;
    }

    @Override
    public String toString() {
        return cls.getSimpleName();
    }

    @Override
    public int hashCode() {
        return cls.hashCode();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (obj instanceof DClass) {
            return cls.equals(((DClass) obj).cls);
        } else {
            return false;
        }
    }

}
