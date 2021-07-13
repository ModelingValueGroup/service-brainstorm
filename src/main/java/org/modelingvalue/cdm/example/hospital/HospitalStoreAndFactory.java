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

package org.modelingvalue.cdm.example.hospital;

import org.modelingvalue.cdm.base.CDMStoreAndFactory;
import org.modelingvalue.cdm.example.hospital.model.Case;
import org.modelingvalue.cdm.example.hospital.model.Condition;
import org.modelingvalue.cdm.example.hospital.model.Hospital;
import org.modelingvalue.cdm.example.hospital.model.Medicine;
import org.modelingvalue.cdm.example.hospital.model.Person;
import org.modelingvalue.cdm.example.hospital.model.Plan;
import org.modelingvalue.cdm.example.hospital.model.Stock;
import org.modelingvalue.cdm.example.hospital.model.Treatment;
import org.modelingvalue.cdm.example.hospital.model.Leg;

public class HospitalStoreAndFactory extends CDMStoreAndFactory {
    protected HospitalStoreAndFactory() {
        super(
                Hospital.class,
                Case.class,
                Condition.class,
                Leg.class,
                Medicine.class,
                Person.class,
                Plan.class,
                Stock.class,
                Treatment.class
        );
    }
}
