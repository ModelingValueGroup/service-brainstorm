//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// (C) Copyright 2018-2019 Modeling Value Group B.V. (http://modelingvalue.org)                                        ~
//                                                                                                                     ~
// Licensed under the GNU Lesser General Public License v3.0 (the 'License'). You may not use this file except in      ~
// compliance with the License. You may obtain a copy of the License at: https://choosealicense.com/licenses/lgpl-3.0  ~
// Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on ~
// an 'AS IS' BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the  ~
// specific language governing permissions and limitations under the License.                                          ~
//                                                                                                                     ~
// Maintainers:                                                                                                        ~
//     Wim Bast, Tom Brus, Ronald Krijgsheld                                                                           ~
// Contributors:                                                                                                       ~
//     Arjan Kok, Carel Bast                                                                                           ~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

package template.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import template.*;

public class EksampleOutputView {
    public Object extract(Case x) {
        return extractCase(x);
    }

    private Map<String, Object> extractCase(Case x) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", x.getId().toString());
        Plan plan = Case.PLAN.get(x);
        if (plan != null) {
            map.put("plan", extractPlan(plan));
        }
        return map;
    }

    private Map<String, Object> extractPlan(Plan x) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", x.getId().toString());
        org.modelingvalue.collections.List<Treatment> treatments = Plan.TREATMENTS.get(x);
        if (treatments != null) {
            map.put("treatments", extractTreatments(treatments));
        }
        return map;
    }

    private List<Object> extractTreatments(org.modelingvalue.collections.List<Treatment> x) {
        List<Object> list = new ArrayList<>();
        x.forEach(xx -> list.add(extractTreatment(xx)));
        return list;
    }

    private Map<String, Object> extractTreatment(Treatment x) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", x.getId().toString());
        return map;
    }
}
