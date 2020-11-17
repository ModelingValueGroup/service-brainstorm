package template;

import org.modelingvalue.collections.Set;
import org.modelingvalue.dclare.Mutable;

import base.MClass;

public class Action implements Mutable {

    private static final MClass<Action> D_CLASS = MClass.of(Action.class, Set.of(), Set.of());

    @Override
    public MClass<Action> dClass() {
        return D_CLASS;
    }

}
