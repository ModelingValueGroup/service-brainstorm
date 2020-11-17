package base;

import org.modelingvalue.collections.Collection;
import org.modelingvalue.collections.Set;
import org.modelingvalue.dclare.MutableClass;
import org.modelingvalue.dclare.Observer;
import org.modelingvalue.dclare.Setable;

public class CDMClass<C extends CDMObject> implements MutableClass {

    private final Class<C>                     cls;
    private final Set<CDMProperty<C, ?>>       properties;
    private final Set<? extends Setable<C, ?>> setables;
    private final Set<? extends Observer<C>>   observers;

    @SafeVarargs
    public static <T extends CDMObject> CDMClass<T> of(Class<T> cls, CDMProperty<T, ?>... properties) {
        return new CDMClass<T>(cls, Collection.of(properties).toSet());
    }

    private CDMClass(Class<C> cls, Set<CDMProperty<C, ?>> properties) {
        this.cls = cls;
        this.properties = properties;
        this.setables = properties.map(CDMProperty::setable).toSet();
        this.observers = properties.map(CDMProperty::observer).notNull().toSet();
    }

    @Override
    public Set<? extends Observer<C>> dObservers() {
        return observers;
    }

    @Override
    public Set<? extends Setable<C, ?>> dSetables() {
        return setables;
    }

    public Set<CDMProperty<C, ?>> properties() {
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
        } else if (obj instanceof CDMClass) {
            return cls.equals(((CDMClass) obj).cls);
        } else {
            return false;
        }
    }

}
