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

package service;

import java.util.*;

public interface ServiceHandler extends Comparable<ServiceHandler> {
    default String getMethodPattern() {
        return null;
    }

    default String getPathPattern() {
        return null;
    }

    List<String> handle(Request r);

    default boolean isMatch(Request r) {
        String methodPattern = getMethodPattern();
        String pathPattern   = getPathPattern();
        return (pathPattern == null || r.path.matches(pathPattern)) && (methodPattern == null || r.method.matches(methodPattern));
    }

    default int compareTo(ServiceHandler o) {
        Comparator<String>         keyComparator = Comparator.nullsLast(Comparator.comparingInt(String::length));
        Comparator<ServiceHandler> m             = Comparator.comparing(ServiceHandler::getMethodPattern, keyComparator);
        Comparator<ServiceHandler> p             = Comparator.comparing(ServiceHandler::getPathPattern, keyComparator);
        Comparator<ServiceHandler> x             = m.thenComparing(p);

        return x.compare(this, o);
    }
}
