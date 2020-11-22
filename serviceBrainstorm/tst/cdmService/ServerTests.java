package cdmService;

import java.io.*;
import java.net.*;
import java.util.*;

import org.junit.jupiter.api.*;

import simpleservice.*;

public class ServerTests {
    @Test
    public void unknownEntrypoint() {
        Assertions.assertThrows(IOException.class, () -> {
            new URL("http://localhost/bla/bla").getContent();
        });
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

    @Test
    public void apiViewTest() {
        Assertions.assertDoesNotThrow(() -> {
            Map<String, Object> map = TestUtils.apiCall(Api.API_PATH+"/view");
            Assertions.assertEquals(2, map.size());
        });
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
        server = null;
    }
}
