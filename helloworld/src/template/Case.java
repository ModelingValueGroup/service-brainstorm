package template;

import org.modelingvalue.collections.Set;
import org.modelingvalue.dclare.Constant;
import org.modelingvalue.dclare.Universe;

import base.MClass;

public class Case implements Universe {

    private static final Constant<Case, Person> PERSON  = Constant.of("PERSON", true, c -> new Person());

    private static final Constant<Case, Plan>   PLAN    = Constant.of("PLAN", true, c -> new Plan());

    private static final MClass<Case>           D_CLASS = MClass.of(Case.class, Set.of(PERSON, PLAN), Set.of());

    @Override
    public MClass<Case> dClass() {
        return D_CLASS;
    }

}
