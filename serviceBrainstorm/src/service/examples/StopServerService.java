package service.examples;

import java.util.*;

import service.*;

public class StopServerService implements SimpleHandler {
    @Override
    public String getMethodPattern() {
        return "STOP";
    }

    @Override
    public List<String> handle(SimpleRequest r) {
        return STOP_SERVER;
    }
}
