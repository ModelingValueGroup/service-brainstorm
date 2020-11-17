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

class CaseTestStatefull {

    @Test
    void test1() {
        CDMProperty.STATEFULL.run(true, () -> {
            Case universe = new Case("test");
            CDMTransaction tx = universe.transaction(() -> {
                Person person = new Person("Wim");
                Leg left = new Leg("left");
                Leg rigth = new Leg("rigth");
                Condition condition1 = new Condition("problem1");
                Condition condition2 = new Condition("problem2");
                universe.setPerson(person);
                person.setLegs(List.of(left, rigth));
                left.setCondition(condition1);
                rigth.setCondition(condition2);
            });
            tx.stop();
            State result = tx.waitForEnd();
            result.run(() -> {
                assertNotNull(universe.getPlan());
                assertEquals(universe.getPlan().getTreatments().size(), 2);
            });
        });
    }

    @Test
    void test2() {
        CDMProperty.STATEFULL.run(true, () -> {
            Case universe = new Case("test");
            CDMTransaction tx = universe.transaction(() -> {
                Person person = new Person("Wim");
                Leg left = new Leg("left");
                Leg rigth = new Leg("rigth");
                universe.setPerson(person);
                person.setLegs(List.of(left, rigth));
                left.setCondition(null);
                rigth.setCondition(null);
            });
            tx.stop();
            State result = tx.waitForEnd();
            result.run(() -> {
                assertNull(universe.getPlan());
            });
        });
    }

}
