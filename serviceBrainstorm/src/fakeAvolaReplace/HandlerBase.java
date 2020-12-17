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

package fakeAvolaReplace;

import java.util.*;
import java.util.stream.*;

import simpleservice.*;

public abstract class HandlerBase<BODY extends SimpleBody> extends SimpleHandlerBase<BODY> {
    public HandlerBase() {
    }

//    public HandlerBase(String methodPattern, String pathPattern) {
//        super(methodPattern, pathPattern);
//    }

    public static List<String> smartConcat(Object... args) {
        List<String> l = new ArrayList<>();
        for (Object a : args) {
            if (a instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) a;
                if (!map.isEmpty()) {
                    l.add("{");
                    map.keySet().stream().sorted().forEach(key -> l.add(String.format("    \"%s\":\"%s\",", key, map.get(key))));
                    l.set(l.size() - 1, l.get(l.size() - 1).replaceAll(",$", "")); // remove last ','
                    l.add("}");
                }
            } else if (a instanceof BaseStream) {
                ((BaseStream<?, ?>) a).spliterator().forEachRemaining(e -> l.add(e.toString()));
            } else if (a instanceof Iterable) {
                ((Iterable<?>) a).spliterator().forEachRemaining(e -> l.add(e.toString()));
            } else if (a != null) {
                l.add(a.toString());
            }
        }
        return l;
    }

}
