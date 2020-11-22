package config;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.*;

import org.modelingvalue.json.*;

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
        return args == null || args.length == 0
                ? new Config(Paths.get("config"))
                : args[0].startsWith("{")
                ? new Config(args[0])
                : new Config(Paths.get(args[0]));

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private final Map<String, Object> config;

    public Config(Path configfile) throws IOException {
        this(String.join("\n", Files.readAllLines(configfile)));
    }

    public Config(String configJson) {
        Object o = FromJson.fromJson(configJson);
        if (!(o instanceof Map<?, ?>)) {
            throw new Error("config must be a json file with map as top element, but is is a " + (o == null ? "<null>" : o.getClass().getName()));
        }
        //noinspection unchecked
        config = (Map<String, Object>) o;
    }

    public Object get(Path path) {
        return get(path, null);
    }

    public Object get(Path path, Object def) {
        AtomicReference<Object> cur = new AtomicReference<>(config);
        for (Path p : path) {
            //noinspection unchecked
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
