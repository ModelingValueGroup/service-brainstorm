package cdmService;

import simpleservice.*;

public class CdmServer extends SimpleDualServer {
    public static CdmServer create() {
        CdmServer server = new CdmServer();

        server.addHandler(TokenManager.getHandler());
        server.addHandler(Api.getHandler());

        return server;
    }
}
