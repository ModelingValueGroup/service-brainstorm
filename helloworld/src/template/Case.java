package template;

import org.modelingvalue.collections.Set;

import base.DClass;
import base.DProperty;
import base.DUniverse;

public class Case extends DUniverse {

    private static final DProperty<Case, Person> PERSON  = DProperty.of("PERSON", true, c -> new Person());

    private static final DProperty<Case, Plan>   PLAN    = DProperty.of("PLAN", true, c -> new Plan());

    private static final DClass<Case>            D_CLASS = DClass.of(Case.class, Set.of(PERSON, PLAN));

    @Override
    public DClass<Case> dClass() {
        return D_CLASS;
    }

}
