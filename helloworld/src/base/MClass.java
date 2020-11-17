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
