package base;

import org.modelingvalue.collections.util.ContextThread.ContextPool;
import org.modelingvalue.dclare.Universe;
import org.modelingvalue.dclare.UniverseTransaction;

public class CDMTransaction extends UniverseTransaction {

    private final Runnable init;

    public CDMTransaction(Universe id, ContextPool pool, Runnable init) {
        super(id, pool, null, MAX_IN_IN_QUEUE, MAX_TOTAL_NR_OF_CHANGES, MAX_NR_OF_CHANGES, MAX_NR_OF_OBSERVED, MAX_NR_OF_OBSERVERS, MAX_NR_OF_HISTORY, null);
        this.init = init;
    }

    @Override
    protected void init() {
        put("$init", () -> {
            universe().init();
            init.run();
        });
    }

}
