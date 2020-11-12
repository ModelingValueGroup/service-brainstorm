package service.examples;

import java.util.*;

import service.*;

public class PostHandler implements ServiceHandler {
    @Override
    public String getMethodPattern() {
        return "POST";
    }

    @Override
    public List<String> handle(Request r) {
        return Arrays.asList("burp post", "👋👋👋");
    }

    public String toString() {
        return "[" + getMethodPattern() + ";" + getPathPattern() + "]";
    }
}
