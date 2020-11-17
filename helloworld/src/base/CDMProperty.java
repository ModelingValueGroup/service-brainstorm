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

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.modelingvalue.collections.Collection;
import org.modelingvalue.collections.util.Context;
import org.modelingvalue.dclare.Constant;
import org.modelingvalue.dclare.Observed;
import org.modelingvalue.dclare.Observer;
import org.modelingvalue.dclare.Setable;

public class CDMProperty<O extends CDMObject, T> {

    public static final Context<Boolean> STATEFULL = Context.of(false);

    public static <C extends CDMObject, V> CDMProperty<C, V> of(Object id, V def) {
        return new CDMProperty<>(id, def, false, null, null);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(Object id, Function<C, V> deriver) {
        return new CDMProperty<>(id, null, false, null, deriver);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(Object id, V def, Function<C, V> deriver) {
        return new CDMProperty<>(id, def, false, null, deriver);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(Object id, boolean containment, Function<C, V> deriver) {
        return new CDMProperty<>(id, null, containment, null, deriver);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(Object id, V def, boolean containment) {
        return new CDMProperty<>(id, def, containment, null, null);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(Object id, V def, Supplier<CDMProperty<?, ?>> opposite) {
        return new CDMProperty<>(id, def, false, opposite, null);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(Object id, Supplier<CDMProperty<?, ?>> opposite, Function<C, V> deriver) {
        return new CDMProperty<>(id, null, false, opposite, deriver);
    }

    public static <C extends CDMObject, V> CDMProperty<C, V> of(Object id, V def, Supplier<CDMProperty<?, ?>> opposite, Function<C, V> deriver) {
        return new CDMProperty<>(id, def, false, opposite, deriver);
    }

    private final Setable<O, T> setable;
    private final Observer<O>   observer;

    protected CDMProperty(Object id, T def, boolean containment, Supplier<CDMProperty<?, ?>> opposite, Function<O, T> deriver) {
        Supplier<Setable<?, ?>> os = opposite != null ? () -> opposite.get().setable : null;
        if (STATEFULL.get()) {
            this.setable = Observed.of(id, false, def, containment, os, null, true);
            this.observer = Observer.of(id, o -> set(o, deriver.apply(o)));
        } else {
            this.setable = Constant.of(id, def, containment, os, null, deriver, true);
            this.observer = null;
        }
    }

    public T set(O object, T value) {
        return setable.set(object, value);
    }

    public <E> T set(O object, BiFunction<T, E, T> function, E element) {
        return setable.set(object, function, element);
    }

    public T get(O object) {
        return setable.get(object);
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

}
