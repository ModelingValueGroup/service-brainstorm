package template;

import base.CDMClass;
import base.CDMObject;

public class Condition extends CDMObject {

    private static final CDMClass<Condition> D_CLASS = CDMClass.of(Condition.class);

    public Condition(Object id) {
        super(id);
    }

    @Override
    public CDMClass<Condition> dClass() {
        return D_CLASS;
    }

}
