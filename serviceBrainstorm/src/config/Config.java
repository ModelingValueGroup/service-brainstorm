//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// (C) Copyright 2018-2021 Modeling Value Group B.V. (http://modelingvalue.org)                                        ~
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

package config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.modelingvalue.json.FromJson;

@SuppressWarnings("unused")
public class Config {
    public static final String DEFAULT_CONFIG_JSON     = "{}";
    public static final String DEFAULT_CONFIG_PROPERTY = "CONFIG";

    public static Config get() {
        return get(DEFAULT_CONFIG_PROPERTY);
    }

    public static Config get(String name) {
        return new Config(System.getProperty(name, DEFAULT_CONFIG_JSON));
    }

    public static Config get(String[] args) throws IOException {
        return args == null || args.length == 0 ? new Config(Paths.get("config")) : args[0].startsWith("{") ? new Config(args[0]) : new Config(Paths.get(args[0]));

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final Map<String, Object> config;

    public Config(Path configfile) throws IOException {
        this(String.join("\n", Files.readAllLines(configfile)));
    }

    @SuppressWarnings("unchecked")
    public Config(String configJson) {
        Object o = FromJson.fromJson(configJson);
        if (!(o instanceof Map<?, ?>)) {
            throw new Error("config must be a json file with map as top element, but is is a " + (o == null ? "<null>" : o.getClass().getName()));
        }
        config = (Map<String, Object>) o;
    }

    public Object get(Path path) {
        return get(path, null);
    }

    @SuppressWarnings("unchecked")
    public Object get(Path path, Object def) {
        AtomicReference<Object> cur = new AtomicReference<>(config);
        for (Path p : path) {
            cur.getAndUpdate(c -> c instanceof Map<?, ?> ? ((Map<String, Object>) c).get(p.toString()) : def);
        }
        return cur.get();
    }

    public int getInt(String s) {
        return getInt(Paths.get(s));
    }

    public int getInt(String s, int def) {
        return getInt(Paths.get(s), def);
    }

    public int getInt(Path path) {
        return getInt(path, -1);
    }

    public int getInt(Path path, int def) {
        Object o = get(path);
        if (o instanceof Number) {
            return ((Number) o).intValue();
        } else {
            return def;
        }
    }
}
