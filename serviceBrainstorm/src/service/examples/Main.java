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

import java.util.*;

import service.*;

public class Main {
    public static void main(String... args) {
        SimpleDualServer server = new SimpleDualServer(11080, 11443);

        server.addHandler(new DefaultHandler());
        server.addHandler(new StopServerHandler());
        server.addHandler(new ApiSettingsHandler());

        server.start();
        server.waitForDone();
    }

    public static class ApiSettingsHandler implements SimpleHandler {
        @Override
        public String getMethodPattern() {
            return "GET";
        }

        @Override
        public String getPathPattern() {
            return "/api/Settings";
        }

        @Override
        public List<String> handle(SimpleRequest r) {
            Map<String, String> map = new HashMap<>();
            map.put("APIVersion", "2.1.13.0");
            map.put("ApiType", "Execution");
            map.put("Authority", "https://login.avo.la");
            map.put("Environment", "Test");
            map.put("IdentityManager", "https://login.avo.la:444");
            map.put("Organisation", "fabhlth");
            map.put("TokenEndpoint", "https://login.avo.la/connect/token");
            return smartConcat(map);
        }
    }
}
