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
import java.util.*;
import java.util.Map.*;

import org.modelingvalue.json.*;

public interface SimpleBody {
    void trace();

    class FormDataBody implements SimpleBody {
        public final Map<String, String> formData;

        public FormDataBody(SimpleRequest request) {
            if (request.contentLength < 0) {
                throw new Error("form data requires Content-Length in header");
            }
            String              s   = new String(read(request.reader, request.contentLength));
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

        @Override
        public void trace() {
            if (formData != null && !formData.isEmpty()) {
                traceMap(formData, "form");
            }
        }
    }

    class JsonBody implements SimpleBody {
        public final Object jsonData;

        public JsonBody(SimpleRequest request) {
            if (request.contentLength < 0) {
                throw new Error("json requires Content-Length in header");
            }
            jsonData = Json.fromJson(new String(read(request.reader, request.contentLength)));
        }

        @Override
        public void trace() {
            if (jsonData != null) {
                traceJson(jsonData, "json");
            }
        }
    }

    class JsonSchemaBody extends JsonBody {
        public JsonSchemaBody(SimpleRequest request) {
            super(request);
        }

        @Override
        public void trace() {
            if (jsonData != null) {
                traceJson(jsonData, "jsonschema");
            }
        }
    }

    class LinesBody implements SimpleBody {
        public final List<String> bodyLines;

        public LinesBody(SimpleRequest request) {
            try {
                List<String> lines = new ArrayList<>();
                if (0 < request.contentLength) {
                    String all = new String(read(request.reader, request.contentLength));
                    lines.addAll(Arrays.asList(all.split("[\n\r][\n\r]*")));
                } else if (request.reader.ready()) {
                    for (String line = request.reader.readLine(); line != null && request.reader.ready(); line = request.reader.readLine()) {
                        lines.add(line);
                    }
                }
                bodyLines = Collections.unmodifiableList(lines);
            } catch (IOException e) {
                throw new Error("could not read body lines", e);
            }
        }

        @Override
        public void trace() {
            if (bodyLines != null && !bodyLines.isEmpty()) {
                traceLines(bodyLines, "body");
            }
        }
    }

    static void traceMap(Map<String, String> formData, final String name) {
        System.out.println("    -------------------------------------------------------------------------");
        formData.entrySet().stream().sorted(Entry.comparingByKey()).forEach(e -> System.out.printf("    >%-30s : %s\n", name + "." + e.getKey(), e.getValue()));
    }

    static void traceJson(Object json, final String name) {
        System.out.println("    -------------------------------------------------------------------------");
        if (json instanceof Map<?, ?>) {
            @SuppressWarnings("unchecked") Map<String, ?> map = (Map<String, ?>) json;
            map.entrySet().stream().sorted(Entry.comparingByKey()).forEach(e -> System.out.printf("    >%-30s : %s\n", name + "." + e.getKey(), e.getValue()));
        } else if (json instanceof List<?>) {
            List<?> list = (List<?>) json;
            list.forEach(e -> System.out.printf("    >%-30s : %s\n", "json[]", e));
        } else {
            System.out.printf("    >%-30s : %s\n", "json", json);
        }
    }

    static void traceLines(List<String> lines, String name) {
        System.out.println("    -------------------------------------------------------------------------");
        System.out.printf("    >%-30s : %s\n", name, lines.size() + " lines");
        lines.forEach(l -> System.out.println("        | " + l));
    }

    static char[] read(BufferedReader reader, int length) {
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
