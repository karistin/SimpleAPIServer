package lucas.base.trace;

import lucas.base.proxy.HttpTraceFactory;
import lucas.base.proxy.IHttpTrace;
import lucas.base.util.HashUtil;
import lucas.base.util.KeyGen;
import lucas.base.util.SysJMX;

import java.util.concurrent.ConcurrentHashMap;

/**
 * packageName    : lucas.base.trace
 * fileName       : TraceMain
 * author         : lucas
 * date           : 2022-10-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-17        lucas       최초 생성
 */
public class TraceMain {
    public static final String[] EMPTY_PARAM = new String[0];
    private static Object lock = new Object();

    public static IHttpTrace http = null;

    public static final class Stat{
        public TraceContext ctx;
        public Object req;
        public Object res;
        public  boolean isStaticContents;

        public Stat(TraceContext ctx, Object req, Object res) {
            this.ctx = ctx;
            this.req = req;
            this.res = res;
        }

        public Stat(TraceContext ctx) {
            this.ctx = ctx;
        }
    }

    public static Object startHttpService(Object req, Object res) {
        try {

            TraceContext ctx = TraceContextManager.getContext();
            if (ctx != null) {
                return null;
            }

            return startHttp(req, res);
        } catch (Throwable t) {
            System.out.println("Start Http Error");
            t.printStackTrace();
        }
        return null;
    }

    public static Object startHttpFilter(Object req, Object res) {
        try {
            TraceContext ctx = TraceContextManager.getContext();
            if (ctx != null) {
                return null;
//                already traceContext made
            }
//            if (TraceContextManager.startForceDiscard()) {

            return startHttp(req, res);
        } catch (Throwable t) {
            System.out.println( "Start Filter Error");
            t.printStackTrace();
        }
        return null;
    }

    private static void initHttp(Object req) {
        synchronized (lock) {
            if (http == null) {
                http = HttpTraceFactory.create(req.getClass().getClassLoader(), req);
            }
        }
    }
    private static Object startHttp(Object req, Object res) {
        if (http == null) {
            initHttp(req);
        }
        return startHttp(req, res, http, false, null);
    }

    private static Object startHttp(Object req, Object res, IHttpTrace http0, boolean isReactive, Object exchange) {

        TraceContext ctx = new TraceContext();
//        TraceContext ctx = TraceContextManager.getContext();


        ctx.thread = Thread.currentThread();
        ctx.threadId = ctx.thread.getId();

        ctx.txid = KeyGen.next();
        ctx.startTime = System.currentTimeMillis();
        ctx.threadName = ctx.thread.getName();
        ctx.startCpu = SysJMX.getCurrentThreadCPU();
//        ctx.bytes = SysJMX.getCurrentThreadAllocBytes(true);

        ctx._req = req;
        ctx._res = res;
        ctx.http = http0;
        TraceContextManager.start(ctx);
//        put traceContextManager
        http0.start(ctx, req, res);
        ctx.serviceName = http0.getRequestURI();
        ctx.serviceHash = HashUtil.hash(ctx.serviceName);
        ctx.http_method = http0.getMethod();
        ctx.http_query = http0.getQueryString();
        ctx.http_content_type = http0.getContentType();
        ctx.remoteIp = http0.getRemoteAddr();



        System.out.println("============================");
        System.out.println("Thread ID : " + ctx.threadId);
        System.out.println("TxID : " + ctx.txid);
//        System.out.println("Method : " + http0.getMethod());
        System.out.println("URI : " + http0.getRequestURI());
//        System.out.println("IP : " + http0.getRemoteAddr());
//        System.out.println("Type : " + http0.getContentType());

//        this.stat = new Stat(ctx, req, res);

        return null;

    }

    public static Object reject(Object stat, Object req, Object res) {
        return null;
    }
    public static void endHttpService(Object stat, Throwable throwable) {
//        if (TraceContextManager.isForceDiscarded()) {
//            TraceContextManager.clearForceDiscard();
//            return;
//         }

        try {

            TraceContext ctx = TraceContextManager.getContext();
//            Stat stat0 = (Stat) stat;
//            TraceContext ctx = stat0.ctx;
//            if (ctx == null) {
//                return;
//            }
//
            Object req = ctx._req;
            Object res = ctx._res;
//            ctx._req = null;
//            ctx._res = null;
            if(ctx.endHttpProcessingStarted == false) {
                endHttpServiceFinal(ctx, req, res, throwable);
            }

        } catch (Throwable throwable1) {
            throwable1.printStackTrace();
        }
    }

    private static void endHttpServiceFinal(TraceContext ctx, Object req, Object res, Throwable throwable) {
//        if (TraceContextManager.isForceDiscarded()) {
//            TraceContextManager.clearForceDiscard();
//            return;
//        }
        synchronized (ctx) {
            if (ctx.endHttpProcessingStarted) {
                System.out.println("[info] duplicated endHttpServiceFinal() called.: " + ctx.serviceName);
                return;
            }
            ctx.endHttpProcessingStarted = true;
        }
        ctx.http.end(ctx, req, res);
        TraceContextManager.clearAllContext(ctx);
//        TraceContextManager.end(ctx);

    }
}
