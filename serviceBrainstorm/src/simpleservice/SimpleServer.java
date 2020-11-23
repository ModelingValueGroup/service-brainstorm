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

package simpleservice;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.security.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;
import java.util.stream.*;

import javax.net.ssl.*;

import config.*;
import simpleservice.SimpleBody.*;

public abstract class SimpleServer {
    public static final Charset                                          ENCODING                 = StandardCharsets.UTF_8;
    public static final ThreadPoolExecutor                               THREAD_POOL              = new ThreadPoolExecutor(0, 8, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), new MyThreadFactory());
    //
    public static final String                                           CONTENT_TYPE_FORM_DATA   = "application/x-www-form-urlencoded";
    public static final String                                           CONTENT_TYPE_JSON        = "application/json";
    public static final String                                           CONTENT_TYPE_JSON_SCHEMA = "application/schema+json";
    //
    private final       String                                           protocol;
    private final       InetSocketAddress                                address;
    private final       ServerSocket                                     serverSocket;
    private final       List<SimpleHandler>                              handlers                 = new ArrayList<>();
    private final       Map<String, Function<SimpleRequest, SimpleBody>> bodyMakerMap             = new HashMap<>();
    private             boolean                                          stop;

    public SimpleServer(String protocol) {
        this(protocol, getDefaultPort(protocol));
    }

    public SimpleServer(String protocol, int port) {
        this.protocol = protocol;
        address = new InetSocketAddress("0.0.0.0", port);
        serverSocket = makeServerSocketUnchecked(address);

        bodyMakerMap.put("", LinesBody::new);
        bodyMakerMap.put(CONTENT_TYPE_FORM_DATA, FormDataBody::new);
        bodyMakerMap.put(CONTENT_TYPE_JSON, JsonBody::new);
        bodyMakerMap.put(CONTENT_TYPE_JSON_SCHEMA, JsonSchemaBody::new);
    }

    @SuppressWarnings("BusyWait")
    public static void waitForDone() {
        while (0 < THREAD_POOL.getActiveCount()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("all server activity stopped.");
    }

    protected abstract ServerSocket makeServerSocket(InetSocketAddress address) throws IOException, GeneralSecurityException;

    protected String getProtocol() {
        return protocol;
    }

    public void addHandler(SimpleHandler handler) {
        handlers.add(handler);
    }

    public void addBodyMaker(String contentType, Function<SimpleRequest, SimpleBody> bodyMaker) {
        bodyMakerMap.put(contentType, bodyMaker);
    }

    public void start() {
        THREAD_POOL.submit(() -> {
            System.out.printf("%-5s server started at %s\n", getProtocol(), address);
            while (!stop) {
                try {
                    Socket socket = serverSocket.accept();
                    THREAD_POOL.submit(() -> handle(socket));
                } catch (Throwable e) {
                    if (!stop) {
                        System.out.println("Problem accepting " + getProtocol() + " connection");
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(getProtocol() + " server stopped at " + address);
        });
    }

    public void stop() {
        try {
            stop = true;
            serverSocket.close();
        } catch (IOException e) {
            // ignore, what can we do if close throws up
        }
    }

    private ServerSocket makeServerSocketUnchecked(InetSocketAddress address) {
        try {
            return makeServerSocket(address);
        } catch (IOException | GeneralSecurityException e) {
            throw new Error("Could not create " + getProtocol() + " socket at " + address, e);
        }
    }

    private void handle(Socket socket) {
        System.out.println(">>> handling " + getProtocol() + " request....");
        try (socket) {
            SimpleRequest request = new SimpleRequest(socket, ENCODING);
            request.setBody(determineBody(request));
            SimpleResponse response = new SimpleResponse(request);
            response.setHandler(determineHandler(request));
            request.trace();
            response.handle();
        } catch (SSLException e) {
            System.err.println("could not make request due to SSL problem (" + e.getMessage() + ")");
        } catch (SocketException e) {
            System.err.println("could not make request due to Socket problem (" + e.getMessage() + ")");
        } catch (IgnoreableError e) {
            System.err.println("ignoreable problem while handling request: " + e.getMessage());
        } catch (Throwable e) {
            System.err.println("problem handling " + getProtocol() + " request");
            e.printStackTrace();
        } finally {
            System.out.println("<<< " + getProtocol() + " request handled.");
        }
    }

    private SimpleHandler determineHandler(SimpleRequest r) {
        List<SimpleHandler> matches = handlers.stream().filter(h -> h.isMatch(r)).sorted().collect(Collectors.toList());
        return matches.isEmpty() ? null : matches.get(0);
    }

    public SimpleBody determineBody(SimpleRequest request) {
        return bodyMakerMap.getOrDefault(request.contentType, bodyMakerMap.get("")).apply(request);
    }

    protected static int getDefaultPort(String protocol) {
        String url = protocol + "://a.b";
        try {
            return Config.get().getInt(protocol + "/port", new URL(url).getDefaultPort());
        } catch (MalformedURLException e) {
            throw new Error("could not determine default port for " + url, e);
        }
    }

    private static class MyThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger();
        private final String        namePrefix;

        MyThreadFactory() {
            namePrefix = "SimpleServer-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
            t.setDaemon(true);
            t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
