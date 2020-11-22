package cdmService;

import java.util.*;
import java.util.concurrent.atomic.*;

import org.modelingvalue.json.*;

import base.*;
import simpleservice.*;
import template.*;

public class EksampleService {
    public static HandlerBase getHandler() {
        return new EksampleService.Handler();
    }

    Object handle(Map<String, Object> mapIn) {
        AtomicReference<Object> result = new AtomicReference<>();
        CDMProperty.STATEFULL.run(false, () -> {
            Object         id = mapIn.get("id");
            Case           c  = new Case(id);
            CDMTransaction tx = c.transaction(() -> new EksampleInputView().augment(c, mapIn));
            tx.stop();
            tx.waitForEnd().run(() -> result.set(new EksampleOutputView().extract(c)));
        });
        return result.get();
    }

    public static class Handler extends HandlerBase {
        public Handler() {
            super("(GET|POST)", Api.EKSAMPLE_PATH);
        }

        @Override
        public void handle(SimpleRequest request, SimpleResponse response) {
            TokenManager.authorize(request);
            @SuppressWarnings("unchecked")
            Object o = new EksampleService().handle((Map<String, Object>) request.jsonData);
            response.addToBody(Json.toJson(o));
        }
    }
}
