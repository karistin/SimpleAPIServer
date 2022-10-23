package lucas.base.trace;

import lucas.base.util.SimpleLru;

import java.util.Map;
import java.util.Set;

import static lucas.base.trace.TraceContext.GetBy.ThreadLocalTxid;
import static lucas.base.trace.TraceContext.GetBy.ThreadLocalTxidByCoroutine;

/**
 * packageName    : lucas.base.trace
 * fileName       : TraceContextManager
 * author         : lucas
 * date           : 2022-10-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-17        lucas       최초 생성
 */
public class TraceContextManager {
    private static SimpleLru<Long, TraceContext> entryByThreadId = new SimpleLru<Long, TraceContext>(10000);
    private static SimpleLru<Long, TraceContext> entryByTxid = new SimpleLru<Long, TraceContext>(10000);

    private static final ThreadLocal<TraceContext> local = new ThreadLocal<TraceContext>();
    private static final ThreadLocal<Long> txidLocal = new ThreadLocal<Long>();

    public static int size() {
        return entryByTxid.size();
    }

    public static Set<Map.Entry<Long, TraceContext>> getContextEntries() {
        return entryByTxid.entrySet();
    }

    public static Set<Map.Entry<Long, TraceContext>> getThreadingContextEntries() {
        return entryByThreadId.entrySet();
    }


//    public static void setContext(long txid){
//        TraceContext tx = new TraceContext();
//        if (local == null) {
//            entryByTxid.put(txid, tx);
//
//        }
//        System.out.println("Already Transaction made");
//    }

    public static TraceContext getContext() {
        Long txid = txidLocal.get();
        TraceContext traceContext = (txid == null ? null : entryByTxid.get(txid));

        return traceContext;
    }

    public static TraceContext getContextByThreadId(long key) {
        return entryByThreadId.get(key);
    }

    public static Long getLocalTxid() {
        return txidLocal.get();
    }


    public static void start(TraceContext o) {
        local.set(o);
        txidLocal.set(o.txid);
        entryByTxid.put(o.txid, o);
        entryByThreadId.put(o.threadId, o);

    }

    public static void takeoverTxid(TraceContext o, long oldTxid) {
        txidLocal.set(o.txid);
        entryByTxid.remove(oldTxid);
        entryByTxid.put(o.txid, o);
    }

    public static void clearAllContext(TraceContext traceContext) {
        traceContext._req = null;
        traceContext._res = null;
        local.set(null);
        entryByTxid.remove(traceContext.txid);
        entryByThreadId.remove(traceContext.threadId);
    }
}
