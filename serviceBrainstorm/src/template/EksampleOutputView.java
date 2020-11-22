package template;

import java.util.*;

public class EksampleOutputView {
    public Object extract(Case x) {
        return extractCase(x);
    }

    private Map<String, Object> extractCase(Case x) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", x.getId().toString());
        if (x.getPlan() != null) {
            map.put("plan", extractPlan(x.getPlan()));
        }
        return map;
    }

    private Map<String, Object> extractPlan(Plan x) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", x.getId().toString());
        if (x.getTreatments() != null) {
            map.put("treatments", extractTreatments(x.getTreatments()));
        }
        return map;
    }

    private List<Object> extractTreatments(org.modelingvalue.collections.List<Treatment> x) {
        List<Object> list = new ArrayList<>();
        x.forEach(xx->list.add(extractTreatment(xx)));
        return list;
    }

    private Map<String, Object> extractTreatment(Treatment x) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", x.getId().toString());
        return map;
    }
}
