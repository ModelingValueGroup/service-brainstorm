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
    private static String recycleToken;

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
        URL                 url = new URL("http://localhost" + Api.TOKEN_PATH);
        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "client_credentials");
        map.put("scope", "cdm-api");
        map.put("client_id", "cdm-test");
        map.put("client_secret", "cdm-not-so-secret");

        return performRequestForm2Json(url, map);
    }

    private static Map<String, Object> performRequestForm2Json(URL url, Map<String, Object> map) throws IOException {
        return performRequest2Json(url, "application/x-www-form-urlencoded", SimpleResponse.renderFormData(map).getBytes());
    }

    private static Map<String, Object> performRequestJson2Json(URL url, Map<String, Object> map) throws IOException {
        return performRequest2Json(url, "application/json", Json.toJson(map).getBytes());
    }

    private static Map<String, Object> performRequest2Json(URL url, String contentType, byte[] bytes) throws IOException {
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
            Object ret = Json.fromJson(stream2String(is));
            //noinspection unchecked
            return ret instanceof Map<?, ?> ? (Map<String, Object>) ret : null;
        } finally {
            http.disconnect();
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> apiCall(String path) throws IOException {
        URLConnection conn = new URL("http://localhost" + path).openConnection();
        conn.setRequestProperty("Authorization", "Bearer " + getRecycledToken());
        String text   = stream2String((InputStream) conn.getContent());
        Object answer = Json.fromJson(text);
        Assertions.assertTrue(answer instanceof Map);
        return (Map<String, Object>) answer;
    }

    static String stream2String(InputStream content) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(content, StandardCharsets.UTF_8))) {
            return br.lines().collect(Collectors.joining("\n"));
        }
    }
}
