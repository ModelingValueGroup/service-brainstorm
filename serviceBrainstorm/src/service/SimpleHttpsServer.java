package service;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.security.*;

import javax.net.ssl.*;

public class SimpleHttpsServer extends SimpleServer {
    public SimpleHttpsServer(int port) {
        super(port);
    }

    @Override
    protected String getProtocol() {
        return "https";
    }

    protected ServerSocket makeServerSocket(InetSocketAddress address) throws IOException, GeneralSecurityException {
        return getSslServerSocketFactory().createServerSocket(address.getPort(), 0, address.getAddress());
    }

    private static SSLServerSocketFactory getSslServerSocketFactory() throws IOException, GeneralSecurityException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(getKeyManagerFactory().getKeyManagers(), null, null);
        return sslContext.getServerSocketFactory();
    }

    private static KeyManagerFactory getKeyManagerFactory() throws IOException, GeneralSecurityException {
        String keyStorePassword = "xxxxxx";
        Path   keyStorePath     = Files.createTempFile("keystore", ".jks");
        Files.delete(keyStorePath);
        CLI.execute("keytool" +
                " -genkeypair" +
                " -keyalg       RSA" +
                " -alias        selfsigned" +
                " -keystore     " + keyStorePath +
                " -storepass    " + keyStorePassword +
                " -dname        CN=localhost,OU=dev,O=mvg,L=here,C=NL");

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(Files.newInputStream(keyStorePath), keyStorePassword.toCharArray());
        Files.delete(keyStorePath);

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());
        return keyManagerFactory;
    }
}
