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

package org.modelingvalue.cdmservice;//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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

//
//package org.modelingvalue.cdmservice;
//
//import org.modelingvalue.cdm.base.CDMSerializationHelper;
//import org.modelingvalue.cdm.base.CDMTransaction;
//import org.modelingvalue.dclare.UniverseTransaction;
//import org.modelingvalue.template.Case;
//import org.modelingvalue.template.HospitalUniverse;
//import org.modelingvalue.template.HospitalUniverseFactory;
//
//public class MessageTest {
//
//
//    public  static void main(String[] args) {
//        String                 id                  = "1";
//        HospitalUniverse       hospitalUniverse    =  new HospitalUniverse(id);
//        UniverseTransaction    universeTransaction = UniverseTransaction.of(hospitalUniverse, CDMTransaction.THE_POOL);
//        CDMSerializationHelper helper              = new CDMSerializationHelper(new HospitalUniverseFactory());
//        CDMDeltaAdaptor        deltaAdaptor        = new CDMDeltaAdaptor("delta-adaptor", universeTransaction, helper );
//
//
//
//        universeTransaction.start(null);
//        universeTransaction.put("build-model", ()->{
//            Case c = new Case("2");
//            HospitalUniverse.CASE.set(hospitalUniverse,c);
//        });
//
//       // universeTransaction.waitForEnd();
//
//        System.out.println(universeTransaction.currentState().toString());
//
//        String json = deltaAdaptor.get();
//        System.err.println(json);
//    }
//}
