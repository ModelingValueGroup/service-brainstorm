package cdmService;

import org.modelingvalue.json.*;

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
    //
    public static final String API_ENDPOINT   = BASE_URL + API_PATH;
    public static final String TOKEN_ENDPOINT = BASE_URL + TOKEN_PATH;
    public static final String INFO_ENDPOINT  = BASE_URL + INFO_PATH;

    public static class ApiInfo {
        public final String apiName       = "cdm";
        public final String apiVersion    = API_VERSION;
        public final String owner         = OWNER_URL;
        public final String apiEndpoint   = API_ENDPOINT;
        public final String tokenEndpoint = TOKEN_ENDPOINT;
    }

    public static HandlerBase getHandler() {
        return new Handler();
    }

    public static class Handler extends HandlerBase {
        public Handler() {
            super("GET", INFO_PATH);
        }

        @Override
        public void handle(SimpleRequest request, SimpleResponse response) {
            if (!TokenManager.TOKEN_MANAGER.isBearer(request.headers.get("Authorization"))) {
                throw new SimpleProblem("not authorized");
            }
            String s = new ToJson().toJson(new ApiInfo());
            response.addToBody(s);
        }
    }
}
