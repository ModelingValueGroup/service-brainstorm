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

import static cdmService.TestUtils.TEST_HTTPS_PORT;
import static cdmService.TestUtils.TEST_HTTP_PORT;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
            Map<String, Object> map = TestUtils.apiCheck(Api.INFO_PATH);

            Assertions.assertEquals(5, map.size());
            Assertions.assertEquals(Api.API_ENDPOINT, map.get("apiEndpoint"));
        });
    }

    @Test
    public void eksampleTest1() throws IOException {
        TestUtils.apiCheck(Api.EKSAMPLE_PATH, "Eksample-test1");
    }

    @Test
    public void eksampleTest2() throws IOException {
        TestUtils.apiCheck(Api.EKSAMPLE_PATH, "Eksample-test2");
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
