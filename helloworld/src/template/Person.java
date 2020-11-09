package template;

import org.modelingvalue.collections.List;
import org.modelingvalue.dclare.Constant;

public class Person {

    private static final Constant<Person, List<Leg>> LEGS = Constant.of("LEGS", List.of());

    public List<Leg> getLegs() {
        return LEGS.get(this);
    }

    public void setLegs(List<Leg> legs) {
        LEGS.set(this, legs);
    }

}
