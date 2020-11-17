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
        if (STATEFULL.get()) {
            this.setable = Observed.of(id, false, def, containment, () -> opposite.get().setable, null, true);
            this.observer = Observer.of(id, o -> set(o, deriver.apply(o)));
        } else {
            this.setable = Constant.of(id, def, containment, () -> opposite.get().setable, null, deriver, true);
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
