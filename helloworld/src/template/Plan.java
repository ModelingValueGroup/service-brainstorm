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

import org.modelingvalue.collections.List;
import org.modelingvalue.collections.Set;

import base.DClass;
import base.DObject;
import base.DProperty;

public class Plan extends DObject {

    private static final DProperty<Plan, List<Action>> ACTIONS = DProperty.of("ACTIONS", List.of(), true);

    private static final DClass<Plan>                  D_CLASS = DClass.of(Plan.class, Set.of(ACTIONS));

    public List<Action> getActions() {
        return ACTIONS.get(this);
    }

    public void setActions(List<Action> actions) {
        ACTIONS.set(this, actions);
    }

    @Override
    public DClass<Plan> dClass() {
        return D_CLASS;
    }

}
