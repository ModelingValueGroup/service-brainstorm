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

import java.io.*;
import java.util.*;

import simpleservice.*;

public abstract class HandlerBase extends SimpleHandlerBase {
    public HandlerBase() {
    }

    public HandlerBase(String methodPattern, String pathPattern) {
        super(methodPattern, pathPattern);
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
