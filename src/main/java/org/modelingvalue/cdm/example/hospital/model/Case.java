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

package org.modelingvalue.cdm.example.hospital.model;

import java.util.function.Function;

import org.modelingvalue.cdm.base.CDMClass;
import org.modelingvalue.cdm.base.CDMObject;
import org.modelingvalue.cdm.base.CDMProperty;


public class Case extends CDMObject {
    public static final Function<Case, Plan> PLAN_RULE = c -> {
        Person  person              = Case.PERSON.get(c);
        boolean someLegHasCondition = Person.LEGS.get(person).anyMatch(l -> Leg.CONDITION.get(l) != null);
        return someLegHasCondition ? new Plan(c) : null;
    };

    public static final  CDMProperty<Case, Person> PERSON  = CDMProperty.of("=person", true);
    public static final  CDMProperty<Case, Plan>   PLAN    = CDMProperty.of("=plan", true, PLAN_RULE);
    public static final  CDMProperty<Case, String> NAME    = CDMProperty.of("=name", null);
    private static final CDMClass<Case>            D_CLASS = CDMClass.of(Case.class, PERSON, PLAN, NAME);

    public Case(Object id) {
        super(id);
    }

    @Override
    public CDMClass<Case> dClass() {
        return D_CLASS;
    }
}
