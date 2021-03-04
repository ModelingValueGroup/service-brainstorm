//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// (C) Copyright 2018-2021 Modeling Value Group B.V. (http://modelingvalue.org)                                        ~
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

import org.modelingvalue.collections.Map;

import base.views.CDMView;
import template.Case;
import template.Condition;
import template.Leg;
import template.Person;

public class EksampleInputView extends CDMView {
    public static void putIntoCase(Case x, Map<String, Object> map) {
        setFromMap(x, map, Case.PERSON, EksampleInputView::makePerson);
    }

    private static Person makePerson(Map<String, Object> map) {
        Person x = new Person(map.get("id"));
        setFromList(x, map, Person.LEGS, EksampleInputView::makeLeg);
        return x;
    }

    private static Leg makeLeg(Map<String, Object> map) {
        Leg x = new Leg(map.get("id"));
        setFromMap(x, map, Leg.CONDITION, EksampleInputView::makeCondition);
        setInteger(x, map, Leg.LENGTH);
        return x;
    }

    private static Condition makeCondition(Map<String, Object> map) {
        Condition x = new Condition(map.get("id"));
        setBoolean(x, map, Condition.SERIOUS);
        return x;
    }
}
