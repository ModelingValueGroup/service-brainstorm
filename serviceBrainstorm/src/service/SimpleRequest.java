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

public class SimpleRequest {
    public final String              method;
    public final String              path;
    public final String              protocol;
    public final Map<String, String> headers;
    public final List<String>        bodyLines;
    public final BufferedWriter      writer;

    public SimpleRequest(Socket socket, Charset encoding) {
        try {
            BufferedReader reader      = new BufferedReader(new InputStreamReader(socket.getInputStream(), encoding.name()));
            String         requestLine = reader.readLine();
            String[]       parts       = requestLine.split(" ", 3);
            method = parts[0];
            path = parts[1];
            protocol = parts[2];
            headers = getHeaderMap(reader);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), encoding.name()));
            if (headers.containsKey("Expect")) {
                writer.write("HTTP/1.1 100 Continue\r\n\r\n");
                writer.flush();
            }
            bodyLines = getBodyLines(reader);
        } catch (Exception e) {
            throw new Error("could not make request", e);
        }
    }

    private static Map<String, String> getHeaderMap(BufferedReader reader) throws IOException {
        Map<String, String> headers = new HashMap<>();
        for (String line = reader.readLine(); !(line == null || line.isEmpty()); line = reader.readLine()) {
            String[] parts = line.split(": *", 2);
            headers.put(parts[0], parts[1]);
        }
        return Collections.unmodifiableMap(headers);
    }

    private List<String> getBodyLines(BufferedReader reader) throws IOException {
        List<String> lines            = new ArrayList<>();
        String       transferEncoding = headers.get("Transfer-Encoding");
        if (transferEncoding == null || "identity".equals(transferEncoding)) {
            return lines;
        }
        for (String line = reader.readLine(); !(line == null || line.isEmpty()); line = reader.readLine()) {
            lines.add(line);
        }
        return Collections.unmodifiableList(lines);
    }

}
