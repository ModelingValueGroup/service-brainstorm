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
import org.modelingvalue.dclare.Mutable;
import org.modelingvalue.dclare.MutableClass;
import org.modelingvalue.dclare.Observer;
import org.modelingvalue.dclare.Setable;

public class MClass<C extends Mutable> implements MutableClass {

    private final Class<C>                     cls;
    private final Set<? extends Setable<C, ?>> setables;
    private final Set<? extends Observer<C>>   observers;

    public static <T extends Mutable> MClass<T> of(Class<T> cls, Set<? extends Setable<T, ?>> setables, Set<? extends Observer<T>> observers) {
        return new MClass<T>(cls, setables, observers);
    }

    private MClass(Class<C> cls, Set<? extends Setable<C, ?>> setables, Set<? extends Observer<C>> observers) {
        this.cls = cls;
        this.setables = setables;
        this.observers = observers;
    }

    @Override
    public Set<? extends Observer<C>> dObservers() {
        return observers;
    }

    @Override
    public Set<? extends Setable<C, ?>> dSetables() {
        return setables;
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
        } else if (obj instanceof MClass) {
            return cls.equals(((MClass) obj).cls);
        } else {
            return false;
        }
    }

}
