//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// (C) Copyright 2018-2020 Modeling Value Group B.V. (http://modelingvalue.org)                                        ~
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
import template.Plan;
import template.Treatment;

@SuppressWarnings("UnnecessaryLocalVariable")
public class EksampleOutputView extends CDMView {
    public static Object extractCase(Case x) {
        Map<String, Object> map = createWithId(x);
        map = addMap(x, map, Case.PLAN, EksampleOutputView::extractPlan);
        return map;
    }

    private static Map<String, Object> extractPlan(Plan x) {
        Map<String, Object> map = createWithId(x);
        map = addList(x, map, Plan.TREATMENTS, EksampleOutputView::extractTreatment);
        return map;
    }

    private static Map<String, Object> extractTreatment(Treatment x) {
        Map<String, Object> map = createWithId(x);
        return map;
    }
}
