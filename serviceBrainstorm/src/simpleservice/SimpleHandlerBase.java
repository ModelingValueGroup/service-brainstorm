package simpleservice;

import java.util.*;

public abstract class SimpleHandlerBase implements SimpleHandler {
    public static final List<String> STOP_SERVER = new ArrayList<>(); // handle() can return this to stop the server
    //
    protected final     String       methodPattern;
    protected final     String       pathPattern;

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
}
