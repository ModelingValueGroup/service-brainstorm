package service;

import java.util.*;

public interface ServiceHandler extends Comparable<ServiceHandler> {
    default String getMethodPattern() {
        return null;
    }

    default String getPathPattern() {
        return null;
    }

    List<String> handle(Request r);

    default boolean isMatch(Request r) {
        String methodPattern = getMethodPattern();
        String pathPattern   = getPathPattern();
        return (pathPattern == null || r.path.matches(pathPattern)) && (methodPattern == null || r.method.matches(methodPattern));
    }

    default int compareTo(ServiceHandler o) {
        Comparator<String>         keyComparator = Comparator.nullsLast(Comparator.comparingInt(String::length));
        Comparator<ServiceHandler> m             = Comparator.comparing(ServiceHandler::getMethodPattern, keyComparator);
        Comparator<ServiceHandler> p             = Comparator.comparing(ServiceHandler::getPathPattern, keyComparator);
        Comparator<ServiceHandler> x             = m.thenComparing(p);

        return x.compare(this, o);
    }
}
