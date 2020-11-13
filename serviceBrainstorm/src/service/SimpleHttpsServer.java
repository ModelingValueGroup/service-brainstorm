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
