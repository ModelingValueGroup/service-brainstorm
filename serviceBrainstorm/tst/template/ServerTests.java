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

package template;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import java.util.stream.*;

import org.junit.jupiter.api.*;
import org.modelingvalue.json.*;

import cdmService.*;
import simpleservice.*;

public class ServerTests {
    @Test
    public void unknownEntrypoint() {
        Assertions.assertThrows(IOException.class, () -> {
            new URL("http://localhost/bla/bla").getContent();
        });
    }

    @Test
    public void apiInfoTest() {
        Assertions.assertDoesNotThrow(() -> {
            String        token = getFreshToken();
            URLConnection conn  = new URL("http://localhost" + Api.INFO_PATH).openConnection();
            conn.setRequestProperty("Authorization", "Bearer " + token);
            InputStream content = (InputStream) conn.getContent();
            String      text    = stream2String(content);

            Object answer = new FromJson().fromJson(text);
            Assertions.assertTrue(answer instanceof Map);

            @SuppressWarnings("unchecked") Map<String, Object> map = (Map<String, Object>) answer;
            Assertions.assertEquals(Api.API_ENDPOINT, map.get("apiEndpoint"));
        });
    }

    @Test
    public void tokenTest() throws IOException {
        Map<String, Object> answer = getFreshTokenMap();

        Assertions.assertEquals(3, answer.size());

        Assertions.assertNotNull(answer.get("access_token"));
        Assertions.assertNotNull(answer.get("expires_in"));
        Assertions.assertNotNull(answer.get("token_type"));

        Assertions.assertEquals(16, answer.get("access_token").toString().length());
        Assertions.assertEquals(3600L, answer.get("expires_in"));
        Assertions.assertEquals("Bearer", answer.get("token_type"));
    }

    //=========================================================================================================================================================
    private static SimpleDualServer server;

    @BeforeAll
    public static void startServer() {
        server = Main.makeServer();
        server.start();
    }

    @AfterAll
    public static void stopServer() {
        server.stop();
        server.waitForDone();
    }

    //=========================================================================================================================================================
    private String getFreshToken() throws IOException {
        return getFreshTokenMap().get("access_token").toString();
    }

    private Map<String, Object> getFreshTokenMap() throws IOException {
        URL                 url = new URL("http://localhost" + Api.TOKEN_PATH);
        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "client_credentials");
        map.put("scope", "cdm-api");
        map.put("client_id", "cdm-test");
        map.put("client_secret", "cdm-not-so-secret");

        return performRequestForm2Json(url, map);
    }

    private Map<String, Object> performRequestForm2Json(URL url, Map<String, Object> map) throws IOException {
        return performRequest2Json(url, "application/x-www-form-urlencoded", SimpleResponse.renderFormData(map).getBytes());
    }

    private Map<String, Object> performRequestJson2Json(URL url, Map<String, Object> map) throws IOException {
        return performRequest2Json(url, "application/json", new ToJson().toJson(map).getBytes());
    }

    private Map<String, Object> performRequest2Json(URL url, String contentType, byte[] bytes) throws IOException {
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setFixedLengthStreamingMode(bytes.length);
        http.setRequestProperty("Content-Type", contentType);
        http.connect();
        try (OutputStream os = http.getOutputStream()) {
            os.write(bytes);
        }
        try (InputStream is = http.getInputStream()) {
            Object ret = new FromJson().fromJson(stream2String(is));
            //noinspection unchecked
            return ret instanceof Map<?, ?> ? (Map<String, Object>) ret : null;
        } finally {
            http.disconnect();
        }
    }

    private String stream2String(InputStream content) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(content, StandardCharsets.UTF_8))) {
            return br.lines().collect(Collectors.joining("\n"));
        }
    }
}
