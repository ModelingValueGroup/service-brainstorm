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
            Case universe = new Case("test");
            Person person = new Person("Wim");
            Leg left = new Leg("left");
            Leg rigth = new Leg("rigth");
            Condition condition = new Condition("problem");
            CDMTransaction tx = universe.transaction(() -> {
                universe.setPerson(person);
                person.setLegs(List.of(left, rigth));
                left.setCondition(condition);
                rigth.setCondition(null);
            });
            tx.stop();
            State result = tx.waitForEnd();
            result.run(() -> {
                assertNotNull(universe.getPlan());
            });
        });
    }

    @Test
    void test2() {
        CDMProperty.STATEFULL.run(false, () -> {
            Case universe = new Case("test");
            Person person = new Person("Wim");
            Leg left = new Leg("left");
            Leg rigth = new Leg("rigth");
            CDMTransaction tx = universe.transaction(() -> {
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
