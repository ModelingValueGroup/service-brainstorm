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


import org.modelingvalue.cdm.base.CDMClass;
import org.modelingvalue.cdm.base.CDMObject;
import org.modelingvalue.cdm.base.CDMProperty;

public class Leg extends CDMObject {
    public static final  CDMProperty<Leg, Condition> CONDITION = CDMProperty.of("=condition", true);
    public static final  CDMProperty<Leg, Integer>   LENGTH    = CDMProperty.of("=length", 100, false);
    private static final CDMClass<Leg>               D_CLASS   = CDMClass.of(Leg.class, CONDITION, LENGTH);

    public Leg(Object id) {
        super(id);
    }

    @Override
    public CDMClass<Leg> dClass() {
        return D_CLASS;
    }
}