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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.modelingvalue.cdm.base.CDMDeltaAdaptor;
import org.modelingvalue.cdm.base.CDMSerializationHelper;
import org.modelingvalue.cdm.base.CDMTransaction;
import org.modelingvalue.cdm.example.hospital.model.Hospital;
import org.modelingvalue.dclare.UniverseTransaction;

public class HospitalSession {
    //TODO make thread safe
    private static final Map<String, HospitalSession> SESSIONS = new HashMap<>(); // TODO: find a way to remove stale sessions

    public static HospitalSession of(HttpSession session) {
        return SESSIONS.computeIfAbsent(session.getId(), HospitalSession::new);
    }

    private final String                  id;
    private final HospitalStoreAndFactory storeAndFactory;
    private final Hospital                universe;
    private final UniverseTransaction     universeTransaction;
    private final CDMSerializationHelper  helper;
    private final CDMDeltaAdaptor         deltaAdaptor;

    private HospitalSession(String id) {
        this.id             = id;
        storeAndFactory     = new HospitalStoreAndFactory();
        universe            = (Hospital) storeAndFactory.findOrCreateInstance("hospital", id);
        universeTransaction = new CDMTransaction(universe);
        helper              = new CDMSerializationHelper(storeAndFactory);
        deltaAdaptor        = new CDMDeltaAdaptor(universeTransaction, helper);
    }

    public String handleDelta(String json) {
        System.err.println(".... passing delta-json to dclare");
        deltaAdaptor.accept(json);

        System.err.println(".... waiting for resulting deltas");
        List<String> deltas = new ArrayList<>();
        while (deltaAdaptor.isBusy()) {
            String s = deltaAdaptor.get();
            System.err.println(".... got delta: " + s);
            deltas.add(s);
        }
        System.err.println(".... dclare idle, got " + deltas.size() + " deltas");
        return String.join("\n", deltas);
    }

    public String init() {
        System.err.println(".... create initial filling");
        //deltaAdaptor.accept();




        System.err.println(".... waiting for resulting deltas");
        List<String> deltas = new ArrayList<>();
        while (deltaAdaptor.isBusy()) {
            String s = deltaAdaptor.get();
            System.err.println(".... got delta: " + s);
            deltas.add(s);
        }
        System.err.println(".... dclare idle, got " + deltas.size() + " deltas");
        return String.join("\n", deltas);
    }
}
