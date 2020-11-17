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
