//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// (C) Copyright 2018-2020 Modeling Value Group B.V. (http://modelingvalue.org)                                        ~
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

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.modelingvalue.collections.Collection;
import org.modelingvalue.collections.util.Context;
import org.modelingvalue.dclare.Constant;
import org.modelingvalue.dclare.Observed;
import org.modelingvalue.dclare.Observer;
import org.modelingvalue.dclare.Setable;
import org.modelingvalue.dclare.SetableModifier;

@SuppressWarnings("unused")
public class CDMProperty<O extends CDMObject, T> {

    public static final Context<Boolean> STATEFULL = Context.of(false);

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, SetableModifier... modifiers) {
        return new CDMProperty<>(name, null, null, null, modifiers);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, V def, SetableModifier... modifiers) {
        return new CDMProperty<>(name, def, null, null, modifiers);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, Function<C, V> deriver, SetableModifier... modifiers) {
        return new CDMProperty<>(name, null, null, deriver, modifiers);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, V def, Function<C, V> deriver, SetableModifier... modifiers) {
        return new CDMProperty<>(name, def, null, deriver, modifiers);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, V def, Supplier<CDMProperty<?, ?>> opposite, SetableModifier... modifiers) {
        return new CDMProperty<>(name, def, opposite, null, modifiers);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, Supplier<CDMProperty<?, ?>> opposite, Function<C, V> deriver, SetableModifier... modifiers) {
        return new CDMProperty<>(name, null, opposite, deriver, modifiers);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, V def, Supplier<CDMProperty<?, ?>> opposite, Function<C, V> deriver, SetableModifier... modifiers) {
        return new CDMProperty<>(name, def, opposite, deriver, modifiers);
    }

    private final Setable<O, T> setable;
    private final Observer<O>   observer;

    protected CDMProperty(String name, T def, Supplier<CDMProperty<?, ?>> opposite, Function<O, T> deriver, SetableModifier... modifiers) {
        Supplier<Setable<?, ?>> os = opposite != null ? () -> opposite.get().setable : null;
        if (STATEFULL.get()) {
            this.setable = Observed.of(name, def, os, modifiers);
            this.observer = deriver != null ? Observer.of(name, o -> set(o, deriver.apply(o))) : null;
        } else {
            this.setable = Constant.of(name, def, os, deriver, modifiers);
            this.observer = null;
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public T set(O object, T value) {
        return setable.set(object, value);
    }

    public <E> T set(O object, BiFunction<T, E, T> function, E element) {
        return setable.set(object, function, element);
    }

    public T get(O object) {
        return setable.get(object);
    }

    public T getDefault() {
        return setable.getDefault();
    }

    public <E> Collection<E> getCollection(O object) {
        return setable.getCollection(object);
    }

    public Setable<O, T> setable() {
        return setable;
    }

    public Observer<O> observer() {
        return observer;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getName() {
        return (String) setable.id();
    }

    @Override
    public int hashCode() {
        return setable.hashCode();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CDMProperty other = (CDMProperty) obj;
        return setable.equals(other.setable);
    }
}
