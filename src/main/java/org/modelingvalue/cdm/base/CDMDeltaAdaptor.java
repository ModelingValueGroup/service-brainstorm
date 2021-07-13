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

package org.modelingvalue.cdm.base;

import org.modelingvalue.dclare.Setable;
import org.modelingvalue.dclare.State;
import org.modelingvalue.dclare.UniverseTransaction;
import org.modelingvalue.dclare.sync.DeltaAdaptor;

public class CDMDeltaAdaptor extends DeltaAdaptor<CDMClass<CDMObject>, CDMObject, Setable<CDMObject, Object>> {
    public CDMDeltaAdaptor(UniverseTransaction tx, CDMSerializationHelper helper) {
        super("delta-adaptor", tx, helper);
    }

    @Override
    public void accept(String delta) {
        System.err.println();
        System.err.println("======================== parsing delta....  ============");
        super.accept(delta);
    }

    @Override
    protected void queueDelta(State pre, State post, Boolean last) {
        System.err.println();
        System.err.println("======================== making delta....  =============");
        super.queueDelta(pre, post, last);
    }

}
