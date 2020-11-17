package template;

import org.modelingvalue.collections.List;
import org.modelingvalue.collections.Set;

import base.DClass;
import base.DObject;
import base.DProperty;

public class Plan extends DObject {

    private static final DProperty<Plan, List<Action>> ACTIONS = DProperty.of("ACTIONS", List.of(), true);

    private static final DClass<Plan>                  D_CLASS = DClass.of(Plan.class, Set.of(ACTIONS));

    public List<Action> getActions() {
        return ACTIONS.get(this);
    }

    public void setActions(List<Action> actions) {
        ACTIONS.set(this, actions);
    }

    @Override
    public DClass<Plan> dClass() {
        return D_CLASS;
    }

}
