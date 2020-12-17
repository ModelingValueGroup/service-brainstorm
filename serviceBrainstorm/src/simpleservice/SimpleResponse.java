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
import java.util.Map.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class SimpleResponse {
    private final SimpleRequest       request;
    private       SimpleHandler       handler;
    //
    private       int                 responseCode = 200;
    private       String              responseName = "OK";
    private final Map<String, Object> headers      = new HashMap<>();
    private final List<String>        body         = new ArrayList<>();

    public SimpleResponse(SimpleRequest request) {
        this.request = request;
    }

    public void setHandler(SimpleHandler handler) {
        this.handler = handler;
    }

    public void handle() throws IOException {
        headers.put("Content-Type", "text/plain; charset=" + SimpleServer.ENCODING.displayName());
        try {
            if (handler == null) {
                throw new SimpleProblem();
            }
            handler.handle(request, this);
        } catch (SimpleProblem prob) {
            headers.clear();
            body.clear();
            setResponse(400, "Bad Request", "application/json");
            addToBody("{\"error\": \"" + prob.getMessage() + "\"}");
        }
        int contentLength = body.stream().mapToInt(l -> l.getBytes(SimpleServer.ENCODING).length + 2).sum();
        headers.put("Content-Length", contentLength);

        trace();

        writeLines(renderResponseLine());
        writeLines(renderHeaders());
        writeLines("");
        writeLines(body);

        request.writer.flush();
        request.writer.close();
    }

    public void trace() {
        System.out.printf("    <%-30s : %s\n", "handler", handler == null ? "<none>" : handler.getClass().getName());
        System.out.printf("    <%-30s : %s\n", "response", renderResponseLine());
        if (!headers.isEmpty()) {
            System.out.println("    -------------------------------------------------------------------------");
            headers.entrySet()
                    .stream()
                    .sorted(Entry.comparingByKey())
                    .forEach(e -> System.out.printf("    <header.%-23s : %s\n", e.getKey(), e.getValue().toString()));
        }
        if (!body.isEmpty()) {
            System.out.println("    -------------------------------------------------------------------------");
            System.out.printf("    <%-30s : %s\n", "body", body.size() + " lines");
            body.forEach(l -> System.out.println("        | " + l));
        }
        System.out.println("    -------------------------------------------------------------------------");
    }

    public void addToBody(String... ss) {
        addToBody(Arrays.asList(ss));
    }

    public void addToBody(Collection<String> l) {
        body.addAll(l);
    }

    public void setResponse(int code, String name, String type) {
        responseCode = code;
        responseName = name;
        headers.put("Content-Type", type);
    }

    private void writeLines(String... lines) {
        writeLines(Arrays.asList(lines));
    }

    private void writeLines(List<String> lines) {
        try {
            for (String line : lines) {
                request.writer.write(line);
                request.writer.write("\r\n");
            }
        } catch (IOException e) {
            throw new Error("could not write response lines: " + lines, e);
        }
    }

    private String renderResponseLine() {
        return "HTTP/1.1 " + responseCode + " " + responseName;
    }

    public List<String> renderHeaders() {
        return renderMap(headers);
    }

    private List<String> renderMap(Map<String, Object> map) {
        return map.entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .map(e -> String.format("%s: %s", e.getKey(), URLEncoder.encode(e.getValue().toString(), SimpleServer.ENCODING)))
                .collect(Collectors.toList());
    }

    public static String renderFormData(Map<String, Object> map) {
        StringBuilder           result = new StringBuilder();
        AtomicReference<String> sep    = new AtomicReference<>("");
        map.entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .forEach(e -> {
                    result.append(sep.get())
                            .append(URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8))
                            .append("=")
                            .append(URLEncoder.encode(e.getValue().toString(), StandardCharsets.UTF_8));
                    sep.set("&");
                });
        return result.toString();
    }
}
