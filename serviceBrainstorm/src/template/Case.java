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

import base.CDMClass;
import base.CDMProperty;
import base.CDMUniverse;

public class Case extends CDMUniverse {

    private static final CDMProperty<Case, Person> PERSON  = CDMProperty.of("PERSON", true);

    private static final CDMProperty<Case, Plan>   PLAN    = CDMProperty.of("PLAN", true,              //
            c -> c.getPerson().getLegs().anyMatch(l -> l.getCondition() != null) ? new Plan(c) : null);

    private static final CDMClass<Case>            D_CLASS = CDMClass.of(Case.class, PERSON, PLAN);

    public Case(Object id) {
        super(id);
    }

    @Override
    public CDMClass<Case> dClass() {
        return D_CLASS;
    }

    public Person getPerson() {
        return PERSON.get(this);
    }

    public void setPerson(Person person) {
        PERSON.set(this, person);
    }

    public Plan getPlan() {
        return PLAN.get(this);
    }

    public void setPlan(Plan plan) {
        PLAN.set(this, plan);
    }

}
