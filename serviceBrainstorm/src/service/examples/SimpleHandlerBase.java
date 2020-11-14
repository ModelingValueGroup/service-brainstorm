package service.examples;

import java.util.*;
import java.util.stream.*;

import service.*;

public abstract class SimpleHandlerBase implements SimpleHandler {
    public static final List<String> STOP_SERVER = new ArrayList<>(); // handle() can return this to stop the server
    //
    private final       String       methodPattern;
    private final       String       pathPattern;

    public SimpleHandlerBase(String methodPattern, String pathPattern) {
        this.methodPattern = methodPattern;
        this.pathPattern = pathPattern;
    }

    @Override
    public String getMethodPattern() {
        return methodPattern;
    }

    @Override
    public String getPathPattern() {
        return pathPattern;
    }

    public boolean isMatch(SimpleRequest r) {
        String methodPattern = getMethodPattern();
        String pathPattern   = getPathPattern();
        return (pathPattern == null || r.path.matches(pathPattern)) && (methodPattern == null || r.method.matches(methodPattern));
    }

    public int compareTo(SimpleHandler o) {
        Comparator<String>        keyComparator = Comparator.nullsLast(Comparator.comparingInt(String::length));
        Comparator<SimpleHandler> m             = Comparator.comparing(SimpleHandler::getMethodPattern, keyComparator);
        Comparator<SimpleHandler> p             = Comparator.comparing(SimpleHandler::getPathPattern, keyComparator);

        return m.thenComparing(p).compare(this, o);
    }

    public static List<String> smartConcat(Object... args) {
        List<String> l = new ArrayList<>();
        for (Object a : args) {
            if (a instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) a;
                if (!map.isEmpty()) {
                    l.add("{");
                    map.keySet().stream().sorted().forEach(key -> l.add(String.format("    \"%s\":\"%s\",", key, map.get(key))));
                    l.set(l.size() - 1, l.get(l.size() - 1).replaceAll(",$", "")); // remove last ','
                    l.add("}");
                }
            } else if (a instanceof BaseStream) {
                ((BaseStream<?, ?>) a).spliterator().forEachRemaining(e -> l.add(e.toString()));
            } else if (a instanceof Iterable) {
                ((Iterable<?>) a).spliterator().forEachRemaining(e -> l.add(e.toString()));
            } else if (a != null) {
                l.add(a.toString());
            }
        }
        return l;
    }
}
