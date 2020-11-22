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
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;
import org.modelingvalue.json.*;

import simpleservice.*;

@SuppressWarnings("unused")
public class TestUtils {
    public static final int    TEST_HTTP_PORT  = 11080;
    public static final int    TEST_HTTPS_PORT = 11443;
    private static      String recycleToken;

    static String getFreshToken() throws IOException {
        return getFreshTokenMap().get("access_token").toString();
    }

    static String getRecycledToken() throws IOException {
        if (recycleToken == null) {
            recycleToken = getFreshToken();
        }
        return recycleToken;
    }

    static Map<String, Object> getFreshTokenMap() throws IOException {
        URL                 url = makeTestUrl(Api.TOKEN_PATH);
        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "client_credentials");
        map.put("scope", "cdm-api");
        map.put("client_id", "cdm-test");
        map.put("client_secret", "cdm-not-so-secret");

        return performRequestForm2Json(url, false, map);
    }

    public static Map<String, Object> performRequestForm2Json(URL url, boolean bearer, Map<String, Object> map) throws IOException {
        return performRequest2Json(url, "application/x-www-form-urlencoded", bearer, SimpleResponse.renderFormData(map).getBytes());
    }

    public static Map<String, Object> performRequestJson2Json(URL url, boolean bearer, Map<String, Object> map) throws IOException {
        return performRequest2Json(url, "application/json", bearer, Json.toJson(map).getBytes());
    }

    private static Map<String, Object> performRequest2Json(URL url, String contentType, boolean bearer, byte[] bytes) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setFixedLengthStreamingMode(bytes.length);
        conn.setRequestProperty("Content-Type", contentType);
        if (bearer) {
            conn.setRequestProperty("Authorization", "Bearer " + getRecycledToken());
        }
        conn.connect();
        try (OutputStream os = conn.getOutputStream()) {
            os.write(bytes);
        }
        try (InputStream is = conn.getInputStream()) {
            Object ret = Json.fromJson(stream2String(is));
            //noinspection unchecked
            return ret instanceof Map<?, ?> ? (Map<String, Object>) ret : null;
        } finally {
            conn.disconnect();
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> apiCall(String path) throws IOException {
        URLConnection conn = makeTestUrl(path).openConnection();
        conn.setRequestProperty("Authorization", "Bearer " + getRecycledToken());
        String text   = stream2String((InputStream) conn.getContent());
        Object answer = Json.fromJson(text);
        Assertions.assertTrue(answer instanceof Map);
        return (Map<String, Object>) answer;
    }

    public static URL makeTestUrl(String path) throws MalformedURLException {
        return new URL("http://localhost:" + TEST_HTTP_PORT + path);
    }

    static String stream2String(InputStream content) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(content, StandardCharsets.UTF_8))) {
            return br.lines().collect(Collectors.joining("\n"));
        }
    }
}
