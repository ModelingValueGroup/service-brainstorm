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

import org.modelingvalue.json.*;

import simpleservice.SimpleBody.*;
import simpleservice.*;

@SuppressWarnings("unused")
public class Api {
    public static final String API_VERSION    = "0.0.1";
    public static final String OWNER_URL      = "https://modelingvalue.nl";
    public static final String BASE_URL       = "http://fabhlth.api.execution.test.modelingvalue.nl";
    //
    public static final String TOKEN_PATH     = "/token";
    public static final String API_PATH       = "/api";
    public static final String INFO_PATH      = API_PATH + "/info";
    public static final String EKSAMPLE_PATH  = API_PATH + "/service/eksample";
    //
    public static final String API_ENDPOINT   = BASE_URL + API_PATH;
    public static final String TOKEN_ENDPOINT = BASE_URL + TOKEN_PATH;
    public static final String INFO_ENDPOINT  = BASE_URL + INFO_PATH;
    public static final String A_ENDPOINT     = BASE_URL + EKSAMPLE_PATH;

    public static class ApiInfo {
        public final String apiName       = "cdm";
        public final String apiVersion    = API_VERSION;
        public final String owner         = OWNER_URL;
        public final String apiEndpoint   = API_ENDPOINT;
        public final String tokenEndpoint = TOKEN_ENDPOINT;
    }

    public static Handler getHandler() {
        return new Handler();
    }

    public static class Handler extends HandlerBase<LinesBody> {
        public Handler() {
            super("(GET|POST)", INFO_PATH, LinesBody.class);
        }

        @Override
        public void handle(SimpleRequest request, SimpleResponse response) {
            TokenManager.authorize(request);
            String s = Json.toJson(new ApiInfo());
            response.addToBody(s);
        }
    }
}
