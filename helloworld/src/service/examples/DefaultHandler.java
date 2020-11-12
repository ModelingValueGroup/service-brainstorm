package service.examples;

import java.util.*;

import service.*;

public class DefaultHandler implements ServiceHandler {
    public List<String> handle(Request r) {
        return Arrays.asList("burp", "👋👋👋");
    }

    public String toString() {
        return "[" + getMethodPattern() + ";" + getPathPattern() + "]";
    }
}
