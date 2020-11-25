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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.modelingvalue.collections.List;
import org.modelingvalue.dclare.State;

import base.CDMProperty;
import base.CDMTransaction;

class CaseTestStateless {

    @Test
    void test1() {
        CDMProperty.STATEFULL.run(false, () -> {
            Case _case = new Case("test");
            CDMTransaction tx = _case.transaction(() -> {
                Person    person     = new Person("Wim");
                Leg       left       = new Leg("left");
                Leg       right      = new Leg("right");
                Condition condition1 = new Condition("problem1");
                Condition condition2 = new Condition("problem2");
                Case.PERSON.set(_case, person);
                Person.LEGS.set(person, List.of(left, right));
                Leg.CONDITION.set(left, condition1);
                Leg.CONDITION.set(right, condition2);
                Leg.LENGTH.set(left, 50);
                Leg.LENGTH.set(right, 199);
                Condition.SERIOUS.set(condition2, true);
            });
            tx.stop();
            State result = tx.waitForEnd();
            result.run(() -> {
                System.err.println("case       = " + _case);
                Plan plan = Case.PLAN.get(_case);
                assertNotNull(plan);
                System.err.println("plan       = " + plan);
                List<Treatment> treatments = Plan.TREATMENTS.get(plan);
                System.err.println("treatments = " + treatments);
                assertNotNull(treatments);
                assertEquals(treatments.size(), 1);
            });
        });
    }

    @Test
    void test2() {
        CDMProperty.STATEFULL.run(false, () -> {
            Case _case = new Case("test");
            CDMTransaction tx = _case.transaction(() -> {
                Person person = new Person("Wim");
                Leg    left   = new Leg("left");
                Leg    right  = new Leg("right");
                Case.PERSON.set(_case, person);
                Person.LEGS.set(person, List.of(left, right));
                Leg.CONDITION.set(left, null);
                Leg.CONDITION.set(right, null);
                Leg.LENGTH.set(left, 50);
                Leg.LENGTH.set(right, 199);
            });
            tx.stop();
            State result = tx.waitForEnd();
            result.run(() -> {
                assertNull(Case.PLAN.get(_case));
            });
        });
    }

}
