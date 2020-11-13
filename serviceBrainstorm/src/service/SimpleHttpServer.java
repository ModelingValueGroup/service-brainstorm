package service;

import java.io.*;
import java.net.*;

public class SimpleHttpServer extends SimpleServer {
    public SimpleHttpServer(int port) {
        super(port);
    }

    @Override
    protected String getProtocol() {
        return "http ";
    }

    @Override
    protected ServerSocket makeServerSocket(InetSocketAddress address) throws IOException {
        return new ServerSocket(address.getPort(), 0, address.getAddress());
    }
}
