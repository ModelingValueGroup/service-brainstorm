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

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.modelingvalue.collections.Collection;
import org.modelingvalue.collections.Set;
import org.modelingvalue.collections.util.Context;
import org.modelingvalue.dclare.Constant;
import org.modelingvalue.dclare.CoreSetableModifier;
import org.modelingvalue.dclare.Observed;
import org.modelingvalue.dclare.Observer;
import org.modelingvalue.dclare.Setable;
import org.modelingvalue.dclare.SetableModifier;

@SuppressWarnings("unused")
public class CDMProperty<O extends CDMObject, T> {
    public static final Context<Boolean> STATEFULL = Context.of(true);

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name) {
        return new CDMProperty<>(name, null, false, null, null);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, V def) {
        return new CDMProperty<>(name, def, false, null, null);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, Function<C, V> deriver) {
        return new CDMProperty<>(name, null, false, null, deriver);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, V def, Function<C, V> deriver) {
        return new CDMProperty<>(name, def, false, null, deriver);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, boolean containment, Function<C, V> deriver) {
        return new CDMProperty<>(name, null, containment, null, deriver);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, @SuppressWarnings("unused") V def, boolean containment, Function<C, V> deriver) {
        return new CDMProperty<>(name, null, containment, null, deriver);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, boolean containment) {
        return new CDMProperty<>(name, null, containment, null, null);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, V def, boolean containment) {
        return new CDMProperty<>(name, def, containment, null, null);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, V def, Supplier<CDMProperty<?, ?>> opposite) {
        return new CDMProperty<>(name, def, false, opposite, null);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, Supplier<CDMProperty<?, ?>> opposite, Function<C, V> deriver) {
        return new CDMProperty<>(name, null, false, opposite, deriver);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(String name, V def, Supplier<CDMProperty<?, ?>> opposite, Function<C, V> deriver) {
        return new CDMProperty<>(name, def, false, opposite, deriver);
    }

    private final Setable<O, T> setable;
    private final Observer<O>   observer;

    protected CDMProperty(String name, T def, boolean containment, Supplier<CDMProperty<?, ?>> opposite, Function<O, T> deriver) {
        Supplier<Setable<?, ?>> os             = opposite != null ? () -> opposite.get().setable : null;
        SetableModifier         containmentMod = containment ? CoreSetableModifier.containment : null;
        if (STATEFULL.get()) {
            this.setable  = Observed.of(name, def, os, (Supplier<Setable<O, Set<?>>>) null, containmentMod);
            this.observer = deriver != null ? Observer.of(name, o -> set(o, deriver.apply(o))) : null;
        } else {
            this.setable  = Constant.of(name, def, os, null, deriver, containmentMod);
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CDMProperty other = (CDMProperty) obj;
        return setable.equals(other.setable);
    }
}
