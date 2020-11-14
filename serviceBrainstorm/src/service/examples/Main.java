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
    public static final String OUR_URL                    = "http://fabhlth.api.execution.test.modelingvalue.nl";
    public static final String AVOLA_AUTHORITY_URL        = "https://login.avo.la";
    public static final String AVOLA_IDENTITY_MANAGER_URL = "https://login.avo.la:444";
    public static final String AVOLA_TOKEN_ENDPOINT_URL   = "https://login.avo.la/connect/token";
    //
    public static final String AUTHORITY_URL              = OUR_URL;
    public static final String IDENTITY_MANAGER_URL       = OUR_URL + "/___/identity/manager";
    public static final String TOKEN_ENDPOINT_URL         = OUR_URL + "/connect/token";

    public static void main(String... args) {
        SimpleDualServer server = new SimpleDualServer(11080, 11443);

        server.addHandler(new DefaultHandler());
        server.addHandler(new StopServerHandler());
        server.addHandler(new ApiSettingsHandler());
        server.addHandler(new ConnectTokenHandler());

        server.start();
        server.waitForDone();
    }

    public static class ApiSettingsHandler extends SimpleHandlerBase {
        public ApiSettingsHandler() {
            super("GET", "/api/Settings");
        }

        @Override
        public List<String> handle(SimpleRequest r) {
            Map<String, String> map = new HashMap<>();
            map.put("APIVersion", "2.1.13.0");
            map.put("ApiType", "Execution");
            map.put("Authority", AUTHORITY_URL);
            map.put("Environment", "Test");
            map.put("IdentityManager", IDENTITY_MANAGER_URL);
            map.put("Organisation", "fabhlth");
            map.put("TokenEndpoint", TOKEN_ENDPOINT_URL);
            return smartConcat(map);
        }
    }

    public static class ConnectTokenHandler extends SimpleHandlerBase {
        public ConnectTokenHandler() {
            super("POST", "/connect/token");
        }

        /*
        grant_type=client_credentials&client_id=apiclient-FabHLTH-79b34b85833f480196d97ca836988acf&client_secret=hDbMIVGxZzD0lDXx4fBmpNpHNXwTJ4vF74hdCYbocU&scope=avola-api-client
        {
          "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IlRaVHlrZEtHaEs1Snd3VjZ6VGlFcFAyNkstayIsImtpZCI6IlRaVHlrZEtHaEs1Snd3VjZ6VGlFcFAyNkstayJ9.eyJpc3MiOiJodHRwczovL2xvZ2luLmF2by5sYSIsImF1ZCI6Imh0dHBzOi8vbG9naW4uYXZvLmxhL3Jlc291cmNlcyIsImV4cCI6MTYwMjMzODA5NSwibmJmIjoxNjAyMzM0NDk1LCJjbGllbnRfaWQiOiJhcGljbGllbnQtRmFiSExUSC03OWIzNGI4NTgzM2Y0ODAxOTZkOTdjYTgzNjk4OGFjZiIsInJvbGUiOiJDdXN0b21lckFwaUNsaWVudCIsIm9yZ2FuaXNhdGlvbiI6IkZhYkhMVEgiLCJzY29wZSI6ImF2b2xhLWFwaS1jbGllbnQifQ.S9pezx5gvgI7cI9ndryQz0loZSOwZ3i7LfUAIahtsEl2A55C-fJyWEjcuyD5uKRmh4KsLQPByHrWA3MLSEfxbd-vvLsHoIpiZkeHO2FaOY7GOgz7qs4dG73anj8A5t3T2jTdti0pUMnHg8YIt8ixW__T8-7PjaKmd95Qu9tcgm8tNqc5sglIfVwTkVoykPI_O9BzALikQ8vKD3nhqcYjyD3qQGN9ANIkHb8AGKsKl8ANkzWp0tf_JxoJNZBpCheshb2-oPXNpgVhVHkMpQEbg1iz2LE1etjoYa3VHUw6_pW_iRth0NgJuaFbGBrniJRQ7To__ACBDnvmCMZJNisQeJlsTZgp3CRdmWANRCgA26oojCBU8HJMTCJcTd6QBA5NTOgYtptc1f6T6EsAl_B8hzcJTxdZ57TWSmt71d4nIwW7CFHohAjRx032iYvfSCwMD4d3L-LhG3ldzJ7Nkwid09DY9Lsf6ds2ld5sw527sAHj6TbxMR9ZN3BYjvJDwhPQ5Dw1H-kM8t2oz_cmdgEXFcsbDzpd5DM_SUQ1Aaf-twUxfz4g3zfj1bbqGkuWW3ouhvOBF4hwIHoF3Tc6ukUrrxSLVceFb6LlkjfOJhoWt12jS17j8h1lXRaa-KDkgC0QWQ9uFpE3ipriWo_A6nt2AtukS6jNMDYx9rfba7SvoS8",
          "expires_in": 3600,
          "token_type": "Bearer"
        }

         */
        @Override
        public List<String> handle(SimpleRequest r) {
            Map<String, String> map = new HashMap<>();
            map.put("APIVersion", "2.1.13.0");
            map.put("ApiType", "Execution");
            map.put("Authority", AUTHORITY_URL);
            map.put("Environment", "Test");
            map.put("IdentityManager", IDENTITY_MANAGER_URL);
            map.put("Organisation", "fabhlth");
            map.put("TokenEndpoint", TOKEN_ENDPOINT_URL);
            return smartConcat(map);
        }
    }

    //  GET /api/ApiExecution/decisions/list
    // POST /connect/token
}
