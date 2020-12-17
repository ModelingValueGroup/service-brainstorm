package template;

import base.CDMClass;
import base.CDMObject;

public class Medicine extends CDMObject {
	
	private static final CDMClass<Medicine> D_CLASS = CDMClass.of(Medicine.class);

	public Medicine(Object id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CDMClass<Medicine> dClass() {
		// TODO Auto-generated method stub
		return D_CLASS;	
	}
	

}
