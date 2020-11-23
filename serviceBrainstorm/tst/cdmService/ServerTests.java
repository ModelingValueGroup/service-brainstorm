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

import static cdmService.TestUtils.TEST_HTTPS_PORT;
import static cdmService.TestUtils.TEST_HTTP_PORT;
import static cdmService.TestUtils.makeTestUrl;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelingvalue.json.Json;

import config.Config;

public class ServerTests {

    @Test
    public void configTest() {
        String jsonConfig = "{\"http\":{\"port\":" + TEST_HTTP_PORT + ",\"other\":\"xyzzy\"},\"https\":{\"port\":" + TEST_HTTPS_PORT + ",\"other\":\"plugh\"}}";

        Config config = new Config(jsonConfig);

        Assertions.assertEquals(TEST_HTTP_PORT, config.getInt(Paths.get("http/port")));
        Assertions.assertEquals(TEST_HTTPS_PORT, config.getInt(Paths.get("https/port")));
    }

    @Test
    public void unknownEntrypoint() {
        Assertions.assertThrows(IOException.class, () -> TestUtils.makeTestUrl("/bla/bla").getContent());
    }

    @Test
    public void tokenTest() throws IOException {
        Map<String, Object> answer = TestUtils.getFreshTokenMap();

        Assertions.assertEquals(3, answer.size());

        Assertions.assertNotNull(answer.get("access_token"));
        Assertions.assertNotNull(answer.get("expires_in"));
        Assertions.assertNotNull(answer.get("token_type"));

        Assertions.assertEquals(16, answer.get("access_token").toString().length());
        Assertions.assertEquals(3600L, answer.get("expires_in"));
        Assertions.assertEquals("Bearer", answer.get("token_type"));
    }

    @Test
    public void apiInfoTest() {
        Assertions.assertDoesNotThrow(() -> {
            Map<String, Object> map = TestUtils.apiCall(Api.INFO_PATH);
            Assertions.assertEquals(5, map.size());
            Assertions.assertEquals(Api.API_ENDPOINT, map.get("apiEndpoint"));
        });
    }

    @SuppressWarnings("unchecked")
    @Test
    public void eksampleTest1() {
        Assertions.assertDoesNotThrow(() -> {
            URL url = makeTestUrl(Api.EKSAMPLE_PATH);
            Map<String, Object> inputMap = (Map<String, Object>) Json.fromJson("{\n" + "  \"id\": \"test\",\n" + "  \"person\": {\n" + "    \"id\": \"Wim\",\n" + "    \"legs\": [\n" + "      {\n" + "        \"id\": \"left\",\n" + "        \"condition\": {\n" + "          \"id\": \"problem1\"\n" + "        }\n" + "      },\n" + "      {\n" + "        \"id\": \"right\",\n" + "        \"condition\": {\n" + "          \"id\": \"problem2\"\n" + "        }\n" + "      }\n" + "    ]\n" + "  }\n" + "}");
            Map<String, Object> outputMap = TestUtils.performRequestJson2Json(url, true, inputMap);

            Assertions.assertEquals(2, outputMap.size());
            Assertions.assertNotNull(outputMap.get("id"));
            Assertions.assertEquals("test", outputMap.get("id"));
            Assertions.assertNotNull(outputMap.get("plan"));
            Assertions.assertTrue(Map.class.isAssignableFrom(outputMap.get("plan").getClass()));
            Map<String, Object> plan = (Map<String, Object>) outputMap.get("plan");

            Assertions.assertEquals(2, plan.size());
            Assertions.assertNotNull(plan.get("id"));
            Assertions.assertEquals("Case:test", plan.get("id"));
            Assertions.assertNotNull(plan.get("treatments"));
            Assertions.assertTrue(List.class.isAssignableFrom(plan.get("treatments").getClass()));
            List<Object> treatments = (List<Object>) plan.get("treatments");

            Assertions.assertEquals(2, treatments.size());
            for (int i : new int[]{0, 1}) {
                Assertions.assertTrue(Map.class.isAssignableFrom(treatments.get(i).getClass()));
                Map<String, Object> treatment = (Map<String, Object>) treatments.get(i);

                Assertions.assertEquals(1, treatment.size());
                Assertions.assertNotNull(treatment.get("id"));
                Assertions.assertEquals("Condition:problem" + (i + 1), treatment.get("id"));
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Test
    public void eksampleTest2() {
        Assertions.assertDoesNotThrow(() -> {
            URL url = makeTestUrl(Api.EKSAMPLE_PATH);
            Map<String, Object> inputMap = (Map<String, Object>) Json.fromJson("{\n" + "  \"id\": \"test\",\n" + "  \"person\": {\n" + "    \"id\": \"Wim\",\n" + "    \"legs\": [\n" + "      {\n" + "        \"id\": \"left\"\n" + "      },\n" + "      {\n" + "        \"id\": \"right\"\n" + "      }\n" + "    ]\n" + "  }\n" + "}");
            Map<String, Object> outputMap = TestUtils.performRequestJson2Json(url, true, inputMap);

            Assertions.assertEquals(1, outputMap.size());
            Assertions.assertNotNull(outputMap.get("id"));
            Assertions.assertEquals("test", outputMap.get("id"));
        });
    }

    //=========================================================================================================================================================
    private static CdmServer server;

    @BeforeAll
    public static void startServer() {
        System.setProperty(Config.DEFAULT_CONFIG_PROPERTY, "{\"http\":{\"port\":" + TEST_HTTP_PORT + "},\"https\":{\"port\":" + TEST_HTTPS_PORT + "}}");
        server = CdmServer.create();
        server.start();
    }

    @AfterAll
    public static void stopServer() {
        server.stop();
        server.waitForDone();
        server = null;
    }
}
