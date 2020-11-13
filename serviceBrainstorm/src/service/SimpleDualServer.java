package service;

public class SimpleDualServer {
    private final SimpleHttpServer  httpServer;
    private final SimpleHttpsServer httpsServer;

    public SimpleDualServer(int httpPort, int httpsPort) {
        httpServer = new SimpleHttpServer(httpPort);
        httpsServer = new SimpleHttpsServer(httpsPort);
    }

    public void addHandler(SimpleHandler handler) {
        httpServer.addHandler(handler);
        httpsServer.addHandler(handler);
    }

    public void start() {
        httpServer.start();
        httpsServer.start();
    }

    public void waitForDone() {
        SimpleServer.waitForDone();
    }
}
