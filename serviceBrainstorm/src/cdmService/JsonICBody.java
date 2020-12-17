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

package cdmService;

import static java.util.Comparator.*;

import org.modelingvalue.collections.*;
import org.modelingvalue.dclare.sync.*;

import simpleservice.*;

public class JsonICBody implements SimpleBody {
    public final Map<String, Object> jsonData;

    @SuppressWarnings("unchecked")
    public JsonICBody(SimpleRequest request) {
        if (request.contentLength < 0) {
            throw new Error("json requires Content-Length in header");
        }
        jsonData = (Map<String, Object>) JsonIC.fromJson(new String(SimpleBody.read(request.reader, request.contentLength)));
    }

    @Override
    public void trace() {
        if (jsonData != null) {
            traceJson(jsonData, "json");
        }
    }

    static void traceJson(Object json, @SuppressWarnings("SameParameterValue") final String name) {
        System.out.println("    -------------------------------------------------------------------------");
        if (json instanceof Map<?, ?>) {
            @SuppressWarnings("unchecked") Map<String, ?> map = (Map<String, ?>) json;
            map.sorted(comparing(Entry::getKey)).forEach(e -> System.out.printf("    >%-30s : %s\n", name + "." + e.getKey(), e.getValue()));
        } else if (json instanceof List<?>) {
            List<?> list = (List<?>) json;
            list.forEach(e -> System.out.printf("    >%-30s : %s\n", "json[]", e));
        } else {
            System.out.printf("    >%-30s : %s\n", "json", json);
        }
    }
}
