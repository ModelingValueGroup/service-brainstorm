package template;

import base.CDMClass;
import base.CDMObject;

public class Behandeling extends CDMObject {

    private static final CDMClass<Behandeling> D_CLASS = CDMClass.of(Behandeling.class);

    @Override
    public CDMClass<Behandeling> dClass() {
        return D_CLASS;
    }

}
