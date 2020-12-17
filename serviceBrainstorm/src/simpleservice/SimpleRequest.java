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

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

public class SimpleRequest {
    public final String              hostAddress;
    public final BufferedReader      reader;
    public final BufferedWriter      writer;
    //
    public       String              method;
    public       String              path;
    public       String              protocol;
    //
    public       boolean             hasExpect;
    public       int                 contentLength;
    public       String              contentType;
    //
    public       Map<String, String> headers;
    private      SimpleBody          body;

    public SimpleRequest(Socket socket, Charset encoding) throws IOException {
        hostAddress = socket.getInetAddress().getHostAddress();
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), encoding.name()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), encoding.name()));
        readRequestLine();
        readHeaders();
        if (1_000_000 < contentLength) {
            throw new Error("body too long: " + contentLength);
        }
        if (hasExpect) {
            writer.write("HTTP/1.1 100 Continue\r\n\r\n");
            writer.flush();
        }
    }

    public void setBody(SimpleBody body) {
        this.body = body;
    }

    public SimpleBody getBody() {
        return body;
    }

    private void readRequestLine() throws IOException {
        String requestLine = reader.readLine();
        if (requestLine == null) {
            throw new IgnoreableError("could not read request line");
        }
        String[] parts = requestLine.split(" ", 3);
        if (parts.length != 3) {
            throw new IgnoreableError("unexpected request line: '" + requestLine + "'");
        }
        method = parts[0];
        path = parts[1];
        protocol = parts[2];
    }

    private void readHeaders() throws IOException {
        Map<String, String> map = new HashMap<>();
        for (String line = reader.readLine(); !(line == null || line.isEmpty()); line = reader.readLine()) {
            String[] parts = line.split(": *", 2);
            if (parts.length != 2) {
                throw new IgnoreableError("unexpected header line: '" + line + "'");
            }
            map.put(parts[0], parts[1]);
        }
        headers = Collections.unmodifiableMap(map);

        hasExpect = headers.containsKey("Expect");
        contentLength = headers.containsKey("Content-Length") ? Integer.parseInt(headers.get("Content-Length")) : -1;
        contentType = headers.get("Content-Type");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void trace() {
        System.out.println("    -------------------------------------------------------------------------");
        System.out.printf("    >%-30s : %s\n", "host", hostAddress);
        System.out.printf("    >%-30s : %s\n", "method", method);
        System.out.printf("    >%-30s : %s\n", "path", path);
        System.out.printf("    >%-30s : %s\n", "protocol", protocol);
        if (headers != null && !headers.isEmpty()) {
            SimpleBody.traceMap(headers, "header");
        }
        if (body != null) {
            body.trace();
        }
        System.out.println("    -------------------------------------------------------------------------");
    }
}
