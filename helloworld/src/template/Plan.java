package template;

import org.modelingvalue.collections.Set;
import org.modelingvalue.dclare.Mutable;

import base.MClass;

public class Plan implements Mutable {

    private static final MClass<Plan> D_CLASS = MClass.of(Plan.class, Set.of(), Set.of());

    @Override
    public MClass<Plan> dClass() {
        return D_CLASS;
    }

}
