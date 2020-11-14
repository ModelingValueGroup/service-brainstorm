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

package service.examples;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import service.*;

public abstract class SimpleHandlerBase implements SimpleHandler {
    public static final List<String> STOP_SERVER = new ArrayList<>(); // handle() can return this to stop the server
    //
    private final       String       methodPattern;
    private final       String       pathPattern;

    public SimpleHandlerBase(String methodPattern, String pathPattern) {
        this.methodPattern = methodPattern;
        this.pathPattern = pathPattern;
    }

    @Override
    public String getMethodPattern() {
        return methodPattern;
    }

    @Override
    public String getPathPattern() {
        return pathPattern;
    }

    public boolean isMatch(SimpleRequest r) {
        String methodPattern = getMethodPattern();
        String pathPattern   = getPathPattern();
        return (pathPattern == null || r.path.matches(pathPattern)) && (methodPattern == null || r.method.matches(methodPattern));
    }

    public int compareTo(SimpleHandler o) {
        Comparator<String>        keyComparator = Comparator.nullsLast(Comparator.comparingInt(String::length));
        Comparator<SimpleHandler> m             = Comparator.comparing(SimpleHandler::getMethodPattern, keyComparator);
        Comparator<SimpleHandler> p             = Comparator.comparing(SimpleHandler::getPathPattern, keyComparator);

        return m.thenComparing(p).compare(this, o);
    }

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

    public static List<String> readResource(String name) {
        List<String> l        = new ArrayList<>();
        String       fullName = SimpleHandlerBase.class.getPackageName().replace('.', '/') + '/' + name;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(SimpleHandlerBase.class.getClassLoader().getResourceAsStream(fullName))))) {
            String line;
            while ((line = br.readLine()) != null) {
                l.add(line);
            }
        } catch (IOException e) {
            throw new Error("problem reading " + name, e);
        }
        return l;
    }
}
