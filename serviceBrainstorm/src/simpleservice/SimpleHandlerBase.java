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

package simpleservice;

import java.io.*;
import java.util.*;

import fakeAvolaReplace.*;

public abstract class SimpleHandlerBase<BODY extends SimpleBody> implements SimpleHandler {
    protected String      methodPattern;
    protected String      pathPattern;
    protected Class<BODY> bodyClass;

    public SimpleHandlerBase() {
    }

    public SimpleHandlerBase(String methodPattern, String pathPattern, Class<BODY> bodyClass) {
        this.methodPattern = methodPattern;
        this.pathPattern = pathPattern;
        this.bodyClass = bodyClass;
    }

    public SimpleHandlerBase<BODY> with(String methodPattern, String pathPattern, Class<BODY> bodyClass) {
        this.methodPattern = methodPattern;
        this.pathPattern = pathPattern;
        this.bodyClass = bodyClass;
        return this;
    }

    @SuppressWarnings("unchecked")
    public BODY castBody(SimpleBody body) {
        return (BODY) body;
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
        return (getPathPattern() == null || r.path.matches(getPathPattern()))
                && (getMethodPattern() == null || r.method.matches(getMethodPattern()))
                && (bodyClass == null || bodyClass.isAssignableFrom(r.getBody().getClass()));
    }

    public int compareTo(SimpleHandler o) {
        Comparator<String>        keyComparator = Comparator.nullsLast(Comparator.comparingInt(String::length));
        Comparator<SimpleHandler> m             = Comparator.comparing(SimpleHandler::getMethodPattern, keyComparator);
        Comparator<SimpleHandler> p             = Comparator.comparing(SimpleHandler::getPathPattern, keyComparator);

        return m.thenComparing(p).compare(this, o);
    }

    public static List<String> readResource(String name) {
        List<String> l        = new ArrayList<>();
        String       fullName = HandlerBase.class.getPackageName().replace('.', '/') + '/' + name;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(HandlerBase.class.getClassLoader().getResourceAsStream(fullName))))) {
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
