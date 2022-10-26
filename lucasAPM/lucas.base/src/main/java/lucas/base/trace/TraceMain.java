package lucas.base.trace;

import lucas.base.proxy.HttpTraceFactory;
import lucas.base.proxy.IHttpTrace;
import lucas.base.sql.Config;
import lucas.base.util.KeyGen;
import lucas.base.util.SysJMX;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
    private static final ThreadLocal<TraceContext> ctx = new ThreadLocal<TraceContext>();
//    static IHttpTrace http0;
//
//    static int i = 0;
    public static Object startHttpService(Object req, Object res) {
        TraceContext ctx = TraceContextManager.getContext();
        if (ctx != null) {
            return null;
        }
        IHttpTrace http0 = HttpTraceFactory.create(req.getClass().getClassLoader(), req);

//        tid.set(Thread.currentThread().getId());
//        TraceContext ctx  = new TraceContext();

//        다른 쓰래드에서 호출되는 경우

        ctx = new TraceContext();
        ctx.thread = Thread.currentThread();
        ctx.threadId = ctx.thread.getId();
        ctx.threadName = ctx.thread.getName();

        ctx.txid = KeyGen.next();
        ctx.startTime = System.currentTimeMillis();
        ctx.startCpu = SysJMX.getCurrentThreadCPU();

        ctx._req = req;
        ctx._res = res;
        ctx.http = http0;
        ctx.serviceName = http0.getRequestURI();
        ctx.http_method = http0.getMethod();
        ctx.http_query = http0.getQueryString();
        ctx.http_content_type = http0.getContentType();

        TraceContextManager.setContext(ctx, ctx.threadId);
        return ctx;
    }



    public static Object startHttpFilter (Object req, Object res){
            System.out.println("Enter Filter");
            return null;
        }
    public static void endHttpService(){
//        System.out.println(Thread.currentThread().getName()+"End");
//        StackTraceElement[] e = Thread.currentThread().getStackTrace();
//        for (StackTraceElement els: e) {
//            System.out.println(els.getClassName() + " : "+els.getMethodName());
//        }


        TraceContext ctx = TraceContextManager.getContext();

        if (ctx == null) {
            return;
        }
        ctx.endTime = System.currentTimeMillis();
        ctx.endCpu = SysJMX.getCurrentThreadCPU();
        ctx.lastestTime = ctx.endTime - ctx.startTime;
        ctx.latestCpu = ctx.endCpu - ctx.startCpu;

        Connection con = Config.getInstance().sqlLogin();
//        System.out.println(ctx.toString());
        String query = "insert into transaction";
        query += "(txid,threadId,startTime,startCpu,latestCpu, lastestTime, endTime, endCpu , serviceName, http_method )";
        query += "values(?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement pstmt = con.prepareStatement(query)){

            pstmt.setString(1, String.valueOf(ctx.txid));
            pstmt.setLong(2, ctx.threadId);
            pstmt.setLong(3, ctx.startTime);
            pstmt.setLong(4, ctx.startCpu);
            pstmt.setLong(5, ctx.latestCpu);
            pstmt.setLong(6, ctx.lastestTime);
            pstmt.setLong(7, ctx.endTime);
            pstmt.setLong(8, ctx.endCpu);
            pstmt.setString(9, ctx.serviceName);
            pstmt.setString(10, ctx.http_method);
            System.out.println(pstmt.toString());
            pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("SQL Error");
        }


        TraceContextManager.clearContext(ctx);
        /*
        * sending to db
        *
        *
        * */
        return ;
    }

}

