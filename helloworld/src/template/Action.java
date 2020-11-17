package template;

import org.modelingvalue.collections.Set;

import base.DClass;
import base.DObject;

public class Action extends DObject {

    private static final DClass<Action> D_CLASS = DClass.of(Action.class, Set.of());

    @Override
    public DClass<Action> dClass() {
        return D_CLASS;
    }

}
