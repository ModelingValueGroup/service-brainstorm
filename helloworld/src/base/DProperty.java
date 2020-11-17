package base;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.modelingvalue.collections.Collection;
import org.modelingvalue.dclare.Constant;
import org.modelingvalue.dclare.Observer;
import org.modelingvalue.dclare.Setable;

public class DProperty<O extends DObject, T> {

    public static <C extends DObject, V> DProperty<C, V> of(Object id, V def) {
        return new DProperty<>(id, def, false, null, null);
    }

    public static <C extends DObject, V> DProperty<C, V> of(Object id, Function<C, V> deriver) {
        return new DProperty<>(id, null, false, null, deriver);
    }

    public static <C extends DObject, V> DProperty<C, V> of(Object id, V def, Function<C, V> deriver) {
        return new DProperty<>(id, def, false, null, deriver);
    }

    public static <C extends DObject, V> DProperty<C, V> of(Object id, boolean containment, Function<C, V> deriver) {
        return new DProperty<>(id, null, containment, null, deriver);
    }

    public static <C extends DObject, V> DProperty<C, V> of(Object id, V def, boolean containment) {
        return new DProperty<>(id, def, containment, null, null);
    }

    public static <C extends DObject, V> DProperty<C, V> of(Object id, V def, Supplier<DProperty<?, ?>> opposite) {
        return new DProperty<>(id, def, false, opposite, null);
    }

    public static <C extends DObject, V> DProperty<C, V> of(Object id, Supplier<DProperty<?, ?>> opposite, Function<C, V> deriver) {
        return new DProperty<>(id, null, false, opposite, deriver);
    }

    public static <C extends DObject, V> DProperty<C, V> of(Object id, V def, Supplier<DProperty<?, ?>> opposite, Function<C, V> deriver) {
        return new DProperty<>(id, def, false, opposite, deriver);
    }

    private final Setable<O, T> setable;
    private final Observer<O>   observer;

    protected DProperty(Object id, T def, boolean containment, Supplier<DProperty<?, ?>> opposite, Function<O, T> deriver) {
        this.setable = Constant.of(id, def, containment, () -> opposite.get().setable, null, deriver, true);
        this.observer = Observer.of(id, o -> set(o, deriver.apply(o)));
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
