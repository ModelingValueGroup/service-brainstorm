package service.examples;

import java.util.*;

import service.*;

public class AapHandler implements ServiceHandler {
    @Override
    public String getPathPattern() {
        return "/aap/.*";
    }

    @Override
    public List<String> handle(Request r) {
        return Arrays.asList("burp aap", "👋👋👋");
    }

    public String toString() {
        return "[" + getMethodPattern() + ";" + getPathPattern() + "]";
    }
}
