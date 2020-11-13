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

package service;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.security.*;
import java.security.cert.*;
import java.util.*;
import java.util.Map.*;
import java.util.concurrent.*;
import java.util.stream.*;

import javax.net.ssl.*;

public class SimpleHttpsServer {
    private static final Charset         ENCODING    = StandardCharsets.UTF_8;
    private static final ExecutorService THREAD_POOL = newCachedThreadPool();

    private final InetSocketAddress    address;
    private final ServerSocket         serverSocket;
    private final List<ServiceHandler> handlers = new ArrayList<>();

    public SimpleHttpsServer(int port) {
        address = new InetSocketAddress("0.0.0.0", port);
        serverSocket = makeServerSocket(address);
    }

    public void addHandler(ServiceHandler handler) {
        handlers.add(handler);
    }

    public void start() {
        System.out.println("https server started at " + address);
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                THREAD_POOL.submit(() -> handle(socket));
            } catch (Throwable e) {
                System.out.println("Problem accepting connection");
                e.printStackTrace();
            }
        }
    }

    private void handle(Socket socket) {
        try (socket) {
            System.out.println(">>> handling request....");
            Request        r       = new Request(socket, ENCODING);
            ServiceHandler handler = determineHandler(r);

            System.out.printf("    %-30s : %s\n", "method", r.method);
            System.out.printf("    %-30s : %s\n", "path", r.path);
            System.out.printf("    %-30s : %s\n", "handler", handler == null ? "<none>" : handler.getClass().getSimpleName());
            r.headers.entrySet().stream().sorted(Entry.comparingByKey()).forEach(e -> System.out.printf("    %-30s : %s\n", "header." + e.getKey(), e.getValue()));

            if (handler != null) {
                List<String> lines = handler.handle(r);
                Stream.concat(Stream.of(
                        "HTTP/1.1 200 OK",
                        "Content-Length: " + lines.stream().mapToInt(l -> l.getBytes(SimpleHttpsServer.ENCODING).length + 2).sum(),
                        "Content-Type: text/plain; charset=" + ENCODING.displayName(),
                        "" // An empty line marks the end of the response's header
                ), lines.stream()).forEach(l -> writeLine(r.writer, l));
                r.writer.flush();
            }
            System.out.println("<<< request handled.");
        } catch (Throwable e) {
            System.err.println("Problem handling request");
            e.printStackTrace();
        }
    }

    private ServiceHandler determineHandler(Request r) {
        List<ServiceHandler> matches = handlers.stream().filter(h -> h.isMatch(r)).sorted().collect(Collectors.toList());
        return matches.isEmpty() ? null : matches.get(0);
    }

    private static void writeLine(BufferedWriter writer, String l) {
        try {
            writer.write(l);
            writer.write("\r\n");
        } catch (IOException e) {
            throw new Error("couln not write response " + l, e);
        }
    }

    private static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, 8, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
    }

    private static ServerSocket makeServerSocket(InetSocketAddress address) {
        try {
            return getSslServerSocketFactory().createServerSocket(address.getPort(), 0, address.getAddress());
        } catch (Exception e) {
            throw new Error("Could not create socket at " + address, e);
        }
    }

    private static SSLServerSocketFactory getSslServerSocketFactory() throws NoSuchAlgorithmException, KeyManagementException, IOException, KeyStoreException, CertificateException, UnrecoverableKeyException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(getKeyManagerFactory().getKeyManagers(), null, null);
        return sslContext.getServerSocketFactory();
    }

    private static KeyManagerFactory getKeyManagerFactory() throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
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
