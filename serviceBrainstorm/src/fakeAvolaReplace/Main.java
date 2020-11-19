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

package fakeAvolaReplace;

import java.util.*;

import simpleservice.*;

public class Main {
    //    public static final String AVOLA_AUTHORITY_URL        = "https://login.avo.la";
    //    public static final String AVOLA_IDENTITY_MANAGER_URL = "https://login.avo.la:444";
    //    public static final String AVOLA_TOKEN_ENDPOINT_URL   = "https://login.avo.la/connect/token";
    //
    public static final String OUR_URL              = "http://fabhlth.api.execution.test.modelingvalue.nl";
    public static final String AUTHORITY_URL        = OUR_URL;
    public static final String IDENTITY_MANAGER_URL = OUR_URL + "/___/identity/manager";
    public static final String TOKEN_ENDPOINT_URL   = OUR_URL + "/connect/token";

    public static void main(String... args) {
        SimpleDualServer server = new SimpleDualServer();

        server.addHandler(new ConnectTokenHandler()/**/.with("POST"/**/, "/connect/token"));
        server.addHandler(new ApiSettingsHandler()/* */.with("GET"/* */, "/api/Settings"));
        server.addHandler(new DecisionListHandler()/**/.with("GET"/* */, "/api/ApiExecution/decisions/list"));
        server.addHandler(new ExecuteHandler()/*     */.with("POST"/**/, "/api/ApiExecution/execute"));

        server.addHandler(new DefaultHandler());

        server.start();
        server.waitForDone();
    }

    public static class ConnectTokenHandler extends HandlerBase {
        @Override
        public void handle(SimpleRequest request, SimpleResponse response) {
            Map<String, String> map = new HashMap<>();
            map.put("access_token", "__bogus__access__token__");
            map.put("expires_in", "3600");
            map.put("token_type", "Bearer");
            response.addToBody(smartConcat(map));
        }
    }

    public static class ApiSettingsHandler extends HandlerBase {
        @Override
        public void handle(SimpleRequest request, SimpleResponse response) {
            Map<String, String> map = new HashMap<>();
            map.put("APIVersion", "2.1.13.0");
            map.put("ApiType", "Execution");
            map.put("Authority", AUTHORITY_URL);
            map.put("Environment", "Test");
            map.put("IdentityManager", IDENTITY_MANAGER_URL);
            map.put("Organisation", "fabhlth");
            map.put("TokenEndpoint", TOKEN_ENDPOINT_URL);
            response.addToBody(smartConcat(map));
        }

    }

    public static class DecisionListHandler extends HandlerBase {
        @Override
        public void handle(SimpleRequest request, SimpleResponse response) {
            response.addToBody( readResource("decision-list.json"));
        }
    }

    public static class ExecuteHandler extends HandlerBase {
        @Override
        public void handle(SimpleRequest request, SimpleResponse response) {
            response.addToBody( readResource("execute.json"));
        }
    }

    public static class DefaultHandler extends HandlerBase {
        public void handle(SimpleRequest request, SimpleResponse response) {
            response.addToBody( Arrays.asList("{", "   'error': 'resistence is futile'", "}"));
        }
    }
}
