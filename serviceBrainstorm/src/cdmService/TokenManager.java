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

import java.time.*;
import java.util.*;
import java.util.Map.*;
import java.util.stream.*;

import org.modelingvalue.json.*;

import simpleservice.SimpleBody.*;
import simpleservice.*;

/*
    see: https://tools.ietf.org/html/draft-ietf-oauth-v2-15#section-5.1
 */
public class TokenManager {
    public static final String CDM_SCOPE         = "cdm-api";
    public static final String CDM_CLIENT_ID     = "cdm-test";
    public static final String CDM_CLIENT_SECRET = "cdm-not-so-secret";

    public static final TokenManager TOKEN_MANAGER = new TokenManager();

    public static Handler getHandler() {
        return new Handler();
    }

    public static void authorize(SimpleRequest request) {
        if (!TokenManager.TOKEN_MANAGER.isBearer(request.headers.get("Authorization"))) {
            throw new SimpleProblem("not authorized");
        }
    }

    private final Map<String, LocalDateTime> activeTokens = new HashMap<>();

    public AccessToken newToken() {
        AccessToken token = new AccessToken();
        synchronized (activeTokens) {
            activeTokens.put(token.access_token, LocalDateTime.now().plusSeconds(token.expires_in));
        }
        return token;
    }

    public boolean isValid(String token) {
        LocalDateTime now = LocalDateTime.now();
        synchronized (activeTokens) {
            activeTokens.keySet().removeAll(activeTokens.entrySet().stream().filter(e -> e.getValue().isBefore(now)).map(Entry::getKey).collect(Collectors.toList()));
        }
        return activeTokens.containsKey(token);
    }

    public boolean isBearer(String authorization) {
        return authorization != null && authorization.startsWith("Bearer ") && isValid(authorization.replaceFirst("^Bearer ", ""));
    }

    @SuppressWarnings("unused")
    public static class AccessToken {
        public final String access_token = String.format("%16x", new Random().nextLong());
        public final int    expires_in   = 3600;
        public final String token_type   = "Bearer";
    }

    private static class Handler extends HandlerBase<FormDataBody> {
        public Handler() {
            super("POST", Api.TOKEN_PATH, FormDataBody.class);
        }

        @Override
        public void handle(SimpleRequest request, SimpleResponse response) {
            Map<String, String> formData = castBody(request.getBody()).formData;
            check(formData, "grant_type", "client_credentials");
            check(formData, "scope", CDM_SCOPE);
            check(formData, "client_id", CDM_CLIENT_ID);
            check(formData, "client_secret", CDM_CLIENT_SECRET);

            response.addToBody(Json.toJson(TOKEN_MANAGER.newToken()));
        }

        private void check(Map<String, String> formData, String k, String v) {
            if (!v.equals(formData.get(k))) {
                throw new SimpleProblem("bad " + k);
            }
        }
    }
}
