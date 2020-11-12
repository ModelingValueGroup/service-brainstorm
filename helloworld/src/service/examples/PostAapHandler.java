package service.examples;

import java.util.*;

import service.*;

public class PostAapHandler implements ServiceHandler {
    @Override
    public String getMethodPattern() {
        return "POST";
    }

    @Override
    public String getPathPattern() {
        return "/aap/.*";
    }

    @Override
    public List<String> handle(Request r) {
        return Arrays.asList("burp post aap", "👋👋👋");
    }
}
