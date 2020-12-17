package template;

import org.modelingvalue.collections.List;
import org.modelingvalue.dclare.MutableClass;

import base.CDMClass;
import base.CDMProperty;
import base.CDMUniverse;

public class Message extends CDMUniverse {
	
	public static final CDMProperty<Message, Case>  CASE  = CDMProperty.of("cases", null, true);
	public static final CDMProperty<Person,  Stock> STOCK = CDMProperty.of("stock", null, true);

	private static final CDMClass<Message> D_CLASS = CDMClass.of(Message.class);
	public Message(Object id) {
		super(id);	
	}

	@Override
	public MutableClass dClass() {
		return D_CLASS;
	}

}
