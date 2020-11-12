package service;

import service.examples.*;

public class Main {
    public static void main(String... args) {
        SimpleHttpsServer server = new SimpleHttpsServer(43034);

        server.addHandler(new DefaultHandler());
        server.addHandler(new AapHandler());
        server.addHandler(new PostHandler());
        server.addHandler(new PostAapHandler());

        server.start();
    }
}
