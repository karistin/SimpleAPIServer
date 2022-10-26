package lucas.base.trace;

import lucas.base.util.KeyGen;
import lucas.base.util.SimpleLru;

import javax.print.DocFlavor;
import java.util.Map;
import java.util.Set;


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
//    private static SimpleLru<Long, TraceContext> entryByTxid = new SimpleLru<Long, TraceContext>(10000);

    private static final ThreadLocal<TraceContext> local = new ThreadLocal<TraceContext>();
    private static final ThreadLocal<Long> txidLocal = new ThreadLocal<Long>();
    private static final ThreadLocal<Long> threadId = new ThreadLocal<>();
//    public static int size() {
//        return entryByTxid.size();
//    }

//    public static Set<Map.Entry<Long, TraceContext>> getContextEntries() {
//        return entryByTxid.entrySet();
//    }

//    public static Set<Map.Entry<Long, TraceContext>> getThreadingContextEntries() {
//        return entryByThreadId.entrySet();
//    }



    public static void setContext(TraceContext ctx , long thId){
        if (threadId != null) {
            entryByThreadId.put(thId, ctx);
            txidLocal.set(ctx.txid);
            local.set(ctx);

        }else {
            System.out.println("Transaction save fail!! ");
        }

    }

    /*
    *
    *
    *
    * */
    public static TraceContext getContext() {
        if (threadId.get() == null) {
//            Transaction not made
            long id = Thread.currentThread().getId();
//            long txid = KeyGen.next();

            threadId.set(id);
//            tx.set(txid);
//            txidLocal.set(KeyGen.next());
//            TraceContext ctx = new TraceContext();
            return null;
        }
//        Transaction already made
        return entryByThreadId.get(threadId.get());

//        if (threadId == null)
//        {
//            threadId = (Thread.currentThread().getId());
//
//            return null;
//        }
//        return entryByThreadId.get(threadId);

//        return traceContext;

    }

    public static TraceContext getContextByThreadId(long key) {
        return entryByThreadId.get(key);
    }
//
//    public static Long getLocalTxid() {
//        return txidLocal.get();
//    }
//
//
//    public static void start(TraceContext o) {
//        local.set(o);
//        txidLocal.set(o.txid);
//        entryByTxid.put(o.txid, o);
//        entryByThreadId.put(o.threadId, o);
//
//    }
//
//    public static void takeoverTxid(TraceContext o, long oldTxid) {
//        txidLocal.set(o.txid);
//        entryByTxid.remove(oldTxid);
//        entryByTxid.put(o.txid, o);
//    }
//
    public static void clearContext(TraceContext traceContext) {
        txidLocal.set(null);
        threadId.set(null);
        local.set(null);

        entryByThreadId.remove(traceContext.threadId);
    }
}
