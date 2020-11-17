package template;

import base.CDMClass;
import base.CDMObject;

public class Aandoening extends CDMObject {

    private static final CDMClass<Aandoening> D_CLASS = CDMClass.of(Aandoening.class);

    @Override
    public CDMClass<Aandoening> dClass() {
        return D_CLASS;
    }

}
