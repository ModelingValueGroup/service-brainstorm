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

import org.modelingvalue.collections.Set;
import org.modelingvalue.collections.util.ContextThread;
import org.modelingvalue.collections.util.ContextThread.ContextPool;
import org.modelingvalue.dclare.DclareConfig;
import org.modelingvalue.dclare.LeafTransaction;
import org.modelingvalue.dclare.State;
import org.modelingvalue.dclare.UniverseTransaction;

public class CDMTransaction extends UniverseTransaction {
    private static final boolean     STATEFULL = CDMProperty.STATEFULL.get();
    public static final  ContextPool THE_POOL  = ContextThread.createPool();

    public CDMTransaction(CDMUniverse id) {
        this(id, null);
    }

    public CDMTransaction(CDMUniverse id, Runnable init) {
        super(id, THE_POOL, new DclareConfig());
        put("$init", () -> {
            universe().init();
            if (init != null) {
                init.run();
            }
        });
    }

    @Override
    protected void init() {
        // keep this to avoid the super implementation, the constructor above does the alternative we want.
    }

    public static CDMUniverse cdmUniverse() {
        return (CDMUniverse) LeafTransaction.getCurrent().universeTransaction().universe();
    }

    @Override
    protected void mainLoop(State start) {
        CDMProperty.STATEFULL.run(STATEFULL, () -> super.mainLoop(start));
    }

    @Override
    protected void handleExceptions(Set<Throwable> errors) {
        super.handleExceptions(errors);
        System.err.println("============================================================");
        System.err.println("EXCEPTIONS IN DCLARE ENGINE: " + errors.size() + " exceptions");
        errors.forEach(e -> {
            System.err.println("============================================================");
            e.printStackTrace();
        });
        System.err.println("============================================================");
    }
}