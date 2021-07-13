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

import static org.modelingvalue.cdm.base.CDMTransaction.cdmUniverse;

import java.util.function.Function;

import org.modelingvalue.cdm.base.CDMClass;
import org.modelingvalue.cdm.base.CDMObject;
import org.modelingvalue.cdm.base.CDMProperty;
import org.modelingvalue.collections.List;

public class Plan extends CDMObject {
    public static final  Function<Plan, List<Treatment>>    TREATMENT_RULE = __ -> {
        Person person = Case.PERSON.get(Hospital.CASE.get(((Hospital) cdmUniverse())));
        return Person.LEGS.get(person)
                .filter(leg -> 100 <= Leg.LENGTH.get(leg))
                .map(Leg.CONDITION::get)
                .notNull()
                .filter(Condition.SERIOUS::get)
                .map(Treatment::new)
                .toList();
    };
    public static final  CDMProperty<Plan, List<Treatment>> TREATMENTS     = CDMProperty.of("=treatments", List.of(), true, TREATMENT_RULE);
    private static final CDMClass<Plan>                     D_CLASS        = CDMClass.of(Plan.class, TREATMENTS);

    public Plan(Object id) {
        super(id);
    }

    @Override
    public CDMClass<Plan> dClass() {
        return D_CLASS;
    }
}
