//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// (C) Copyright 2018-2020 Modeling Value Group B.V. (http://modelingvalue.org)                                        ~
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

import java.util.function.*;

public class SimpleDualServer {
    private final SimpleHttpServer  httpServer;
    private final SimpleHttpsServer httpsServer;

    public SimpleDualServer() {
        httpServer = new SimpleHttpServer();
        httpsServer = new SimpleHttpsServer();
    }

    public void addHandler(SimpleHandler handler) {
        httpServer.addHandler(handler);
        httpsServer.addHandler(handler);
    }

    public void addBodyMaker(String contentType, Function<SimpleRequest, SimpleBody> bodyMaker) {
        httpServer.addBodyMaker(contentType, bodyMaker);
        httpsServer.addBodyMaker(contentType, bodyMaker);
    }

    public void start() {
        httpServer.start();
        httpsServer.start();
    }

    public void stop() {
        httpServer.stop();
        httpsServer.stop();
    }

    public void waitForDone() {
        SimpleServer.waitForDone();
    }
}
