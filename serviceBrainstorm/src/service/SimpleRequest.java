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
import java.util.*;

import javax.net.ssl.*;

public class SimpleRequest {
    public String              hostAddress;
    public String              method;
    public String              path;
    public String              protocol;
    //
    public BufferedReader      reader;
    public BufferedWriter      writer;
    //
    public boolean             hasExpect;
    public int                 contentLength;
    public String              contentType;
    //
    public Map<String, String> headers;
    public Map<String, String> formData;
    public List<String>        bodyLines;

    public SimpleRequest(Socket socket, Charset encoding) {
        try {
            hostAddress = socket.getInetAddress().getHostAddress();
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), encoding.name()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), encoding.name()));
            readRequestLine();
            readHeaders();
            readBody();
        } catch (SSLException e) {
            if (e.getMessage().matches("(Unsupported or unrecognized SSL message|The size of the handshake message.*|Connection reset|no cipher suites in common)")) {
                throw new IgnoreableError("could not determine request: " + e.getMessage());
            }
            throw new Error("could not make request", e);
        } catch (SocketException e) {
            if (e.getMessage().equals("Connection reset")) {
                throw new IgnoreableError("could not determine request: " + e.getMessage());
            }
            throw new Error("could not make request", e);
        } catch (Exception e) {
            throw new Error("could not make request", e);
        }
    }

    private void readRequestLine() throws IOException {
        String requestLine = reader.readLine();
        if (requestLine == null) {
            throw new IgnoreableError("could not read request line");
        }
        String[] parts = requestLine.split(" ", 3);
        if (parts.length != 3) {
            throw new Error("unexpected request line: '" + requestLine + "'");
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

    private void readBody() throws IOException {
        if (1_000_000 < contentLength) {
            throw new Error("body too long: " + contentLength);
        }
        if (hasExpect) {
            writer.write("HTTP/1.1 100 Continue\r\n\r\n");
            writer.flush();
        }

        if ("application/x-www-form-urlencoded".equals(contentType)) {
            readFormData();
        } else if ("application/json".equals(contentType)) {
            readJson();
        } else {
            readBodyLines();
        }
    }

    private void readFormData() {
        if (contentLength < 0) {
            throw new Error("form data requires Content-Length in header");
        }
        String              s   = new String(read(reader, contentLength));
        String[]            kvs = s.split("&");
        Map<String, String> map = new HashMap<>(kvs.length);
        for (String kv : kvs) {
            String[] k_v = kv.split("=", 2);
            String   k   = URLDecoder.decode(k_v[0], Charset.defaultCharset());
            String   v   = k_v.length == 2 ? URLDecoder.decode(k_v[1], Charset.defaultCharset()) : null;
            map.put(k, v);
        }
        formData = Collections.unmodifiableMap(map);
    }

    private void readJson() {
        if (contentLength < 0) {
            throw new Error("json requires Content-Length in header");
        }
        bodyLines = Collections.unmodifiableList(Collections.singletonList(new String(read(reader, contentLength))));
    }

    private void readBodyLines() throws IOException {
        List<String> lines = new ArrayList<>();
        if (0 < contentLength) {
            String all = new String(read(reader, contentLength));
            lines.addAll(Arrays.asList(all.split("[\n\r][\n\r]*")));
        } else if (reader.ready()) {
            for (String line = reader.readLine(); line != null && reader.ready(); line = reader.readLine()) {
                lines.add(line);
            }
        }
        bodyLines = Collections.unmodifiableList(lines);
    }

    private static char[] read(BufferedReader reader, int length) {
        char[] buf = new char[length];
        try {
            int read = 0;
            while (read < length) {
                int r = reader.read(buf, read, length - read);
                if (r == -1) {
                    break;
                }
                read += r;
            }
            if (length != read) {
                throw new Error("form data is not of the expected length (" + length + " expected but only " + read + " could be read");
            }
        } catch (IOException e) {
            throw new Error("problem reading form data", e);
        }
        return buf;
    }
}
