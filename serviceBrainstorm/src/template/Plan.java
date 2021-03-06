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

import static base.CDMTransaction.*;

import org.modelingvalue.collections.*;

import base.*;

public class Plan extends CDMObject {

    private static final CDMProperty<Plan, List<Treatment>> TREATMENTS = CDMProperty.of(
            "TREATMENTS",
            List.of(), true,
            p -> ((Case) cdmUniverse()).getPerson().getLegs().map(Leg::getCondition).notNull().map(Treatment::new).toList()
    );

    private static final CDMClass<Plan> D_CLASS = CDMClass.of(Plan.class, TREATMENTS);

    public Plan(Object id) {
        super(id);
    }

    public List<Treatment> getTreatments() {
        return TREATMENTS.get(this);
    }

    public void setTreatments(List<Treatment> treatments) {
        TREATMENTS.set(this, treatments);
    }

    @Override
    public CDMClass<Plan> dClass() {
        return D_CLASS;
    }

}
