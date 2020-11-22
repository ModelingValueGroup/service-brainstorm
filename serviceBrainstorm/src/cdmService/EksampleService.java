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

package cdmService;

import java.util.*;
import java.util.concurrent.atomic.*;

import org.modelingvalue.json.*;

import base.*;
import simpleservice.*;
import template.*;

public class EksampleService {
    public static HandlerBase getHandler() {
        return new EksampleService.Handler();
    }

    Object handle(Map<String, Object> mapIn) {
        AtomicReference<Object> result = new AtomicReference<>();
        CDMProperty.STATEFULL.run(false, () -> {
            Object         id = mapIn.get("id");
            Case           c  = new Case(id);
            CDMTransaction tx = c.transaction(() -> new EksampleInputView().augment(c, mapIn));
            tx.stop();
            tx.waitForEnd().run(() -> result.set(new EksampleOutputView().extract(c)));
        });
        return result.get();
    }

    public static class Handler extends HandlerBase {
        public Handler() {
            super("(GET|POST)", Api.EKSAMPLE_PATH);
        }

        @Override
        public void handle(SimpleRequest request, SimpleResponse response) {
            TokenManager.authorize(request);
            @SuppressWarnings("unchecked")
            Object o = new EksampleService().handle((Map<String, Object>) request.jsonData);
            response.addToBody(Json.toJson(o));
        }
    }
}
