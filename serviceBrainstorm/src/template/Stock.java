package template;

import org.modelingvalue.collections.List;

import base.CDMClass;
import base.CDMObject;
import base.CDMProperty;

public class Stock extends CDMObject {
	
	public static final CDMProperty<Stock, List<Medicine>> MEDICINES = CDMProperty.of("medicines", List.of(), true);
	
	private static final CDMClass<Stock> D_CLASS = CDMClass.of(Stock.class);

	public Stock(Object id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CDMClass<Stock> dClass() {
		// TODO Auto-generated method stub
		return D_CLASS;
	}

}
