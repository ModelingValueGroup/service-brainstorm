package template;

import java.util.Map;
import java.util.*;
import java.util.stream.*;

import org.modelingvalue.collections.List;

@SuppressWarnings("unchecked")
public class EksampleInputView {
    public void augment(Case x, Map<String, Object> map) {
        x.setPerson(makePerson((Map<String, Object>) map.get("person")));
        /// x.setPlan(...); // not in view
    }

    private Person makePerson(Map<String, Object> map) {
        Person x = new Person(map.computeIfAbsent("id", k -> newPersonId()));
        x.setLegs(makeLegs((java.util.List<Object>) map.get("legs")));
        return x;
    }

    private List<Leg> makeLegs(java.util.List<Object> list) {
        List<Leg> x = List.of();
        if (list != null) {
            x = List.of(list.stream().map(m -> makeLeg((Map<String, Object>) m)).collect(Collectors.toList()));
        }
        return x;
    }

    private Leg makeLeg(Map<String, Object> map) {
        Leg x = new Leg(map.computeIfAbsent("id", k -> newLegId()));
        x.setCondition(makeCondition((java.util.Map<String, Object>) map.get("condition")));
        // no more in view
        return x;
    }

    private Condition makeCondition(Map<String, Object> map) {
        Condition x = null;
        if (map != null) {
            x = new Condition(map.computeIfAbsent("id", k -> newConditionId()));
            // nothing in view
        }
        return x;
    }

    private Object newPersonId() {
        return String.format("person-%09d", new Random().nextInt());
    }

    private Object newLegId() {
        return String.format("leg-%09d", new Random().nextInt());
    }

    private Object newConditionId() {
        return String.format("condition-%09d", new Random().nextInt());
    }
}
