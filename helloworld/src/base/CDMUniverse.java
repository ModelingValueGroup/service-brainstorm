package base;

import org.modelingvalue.collections.util.ContextThread;
import org.modelingvalue.collections.util.ContextThread.ContextPool;
import org.modelingvalue.dclare.Universe;
import org.modelingvalue.dclare.UniverseTransaction;

public abstract class CDMUniverse extends CDMObject implements Universe {

    private static final ContextPool THE_POOL = ContextThread.createPool();

    public UniverseTransaction transaction() {
        return UniverseTransaction.of(this, THE_POOL);
    }

}
