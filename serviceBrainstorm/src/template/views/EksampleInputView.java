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

import org.modelingvalue.collections.*;

import base.views.*;
import template.*;

@SuppressWarnings("unchecked")
public class EksampleInputView extends CDMView {
    public void augment(Case x, Map<String, Object> map) {
        Case.PERSON.set(x, makePerson((Map<String, Object>) map.get("person")));
        /// x.setPlan(...); // not in view
    }

    private Person makePerson(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        Person x = new Person(map.get("id"));
        Person.LEGS.set(x, makeLegs((List<Object>) map.get("legs")));
        // no more in view
        return x;
    }

    private List<Leg> makeLegs(List<Object> list) {
        return list == null ? List.of() : list.map(m -> makeLeg((Map<String, Object>) m)).toList();
    }

    private Leg makeLeg(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        Leg x = new Leg(map.get("id"));
        Leg.CONDITION.set(x, makeCondition((Map<String, Object>) map.get("condition")));
        // no more in view
        return x;
    }

    private Condition makeCondition(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        @SuppressWarnings("UnnecessaryLocalVariable")
        Condition x = new Condition(map.get("id"));
        // nothing in view
        return x;
    }
}
