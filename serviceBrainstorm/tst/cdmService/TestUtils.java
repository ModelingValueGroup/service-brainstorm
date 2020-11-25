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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.modelingvalue.json.Json;
import org.modelingvalue.json.JsonPrettyfier;

import simpleservice.SimpleResponse;
import simpleservice.Utils;

@SuppressWarnings("unused")
public class TestUtils {
    public static final int    TEST_HTTP_PORT  = 11080;
    public static final int    TEST_HTTPS_PORT = 11443;
    private static      String recycleToken;

    public static void apiCheck(String entryPoint, String name) throws IOException {
        String inJson  = String.join("\n", Utils.readResource(name + "-in.json", TestUtils.class));
        String outJson = String.join("\n", Utils.readResource(name + "-out.json", TestUtils.class));
        apiCheck(entryPoint, inJson, outJson);
    }

    @SuppressWarnings("unchecked")
    public static void apiCheck(String entryPoint, String in, String out) throws IOException {
        Map<String, Object> inputMap  = (Map<String, Object>) Json.fromJson(in);
        Map<String, Object> outputMap = performRequestJson2Json(makeTestUrl(entryPoint), inputMap);
        Assertions.assertEquals(out, JsonPrettyfier.pretty(Json.toJson(outputMap)));
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> apiCheck(String path) throws IOException {
        URLConnection conn = makeTestUrl(path).openConnection();
        conn.setRequestProperty("Authorization", "Bearer " + getRecycledToken());
        String text   = stream2String((InputStream) conn.getContent());
        Object answer = Json.fromJson(text);
        Assertions.assertTrue(answer instanceof Map);
        return (Map<String, Object>) answer;
    }

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

        return performRequestForm2Json(url, map);
    }

    private static Map<String, Object> performRequestForm2Json(URL url, Map<String, Object> map) throws IOException {
        return performRequest2Json(url, "application/x-www-form-urlencoded", false, SimpleResponse.renderFormData(map).getBytes());
    }

    private static Map<String, Object> performRequestJson2Json(URL url, Map<String, Object> map) throws IOException {
        return performRequest2Json(url, "application/json", true, Json.toJson(map).getBytes());
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> performRequest2Json(URL url, String contentType, boolean authorize, byte[] bytes) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setFixedLengthStreamingMode(bytes.length);
        conn.setRequestProperty("Content-Type", contentType);
        if (authorize) {
            conn.setRequestProperty("Authorization", "Bearer " + getRecycledToken());
        }
        conn.connect();
        try (OutputStream os = conn.getOutputStream()) {
            os.write(bytes);
        }
        try (InputStream is = conn.getInputStream()) {
            String s   = stream2String(is);
            Object ret = Json.fromJson(s);
            return ret instanceof Map<?, ?> ? (Map<String, Object>) ret : null;
        } finally {
            conn.disconnect();
        }
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
