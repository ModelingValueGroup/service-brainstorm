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

    private List<Leg> makeLegs(java.util.List<Object> legs) {
        return List.of(legs.stream().map(m -> makeLeg((Map<String, Object>) m)).collect(Collectors.toList()));
    }

    private Leg makeLeg(Map<String, Object> map) {
        Leg x = new Leg(map.computeIfAbsent("id", k -> newLegId()));
        x.setCondition(makeCondition((java.util.Map<String, Object>) map.get("condition")));
        // no more in view
        return x;
    }

    private Condition makeCondition(Map<String, Object> map) {
        Condition x = new Condition(map.computeIfAbsent("id", k -> newConditionId()));
        // nothing in view
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
