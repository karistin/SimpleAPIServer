package lucas.base.trace;

import lucas.base.proxy.HttpTraceFactory;
import lucas.base.proxy.IHttpTrace;
import lucas.base.sql.Config;
import lucas.base.util.KeyGen;
import lucas.base.util.SysJMX;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
            return ctx;
        }

        IHttpTrace http0 = HttpTraceFactory.create(req.getClass().getClassLoader(), req);

//        tid.set(Thread.currentThread().getId());
//        TraceContext ctx  = new TraceContext();

//        다른 쓰래드에서 호출되는 경우

        ctx = new TraceContext();

        ctx.collectTime = Timestamp.valueOf(LocalDateTime.now());
        ctx.stTime = Timestamp.valueOf(LocalDateTime.now());
        ctx.thread = Thread.currentThread();
        ctx.threadId = ctx.thread.getId();
        ctx.threadName = ctx.thread.getName();

        ctx.txid = KeyGen.next();
        ctx.startTime = System.currentTimeMillis();
        ctx.startCpu = SysJMX.getCurrentThreadCPU();

        ctx._req = req;
        ctx._res = res;
        ctx.http = http0;
        ctx.serviceName = http0.getRequestURI(req);
        ctx.http_method = http0.getMethod(req);
        ctx.http_query = http0.getQueryString(req);
        ctx.remoteIp = http0.getRemoteAddr(req);
//        ctx.http_content_type = http0.getContentType(req);

        ctx.serverName = http0.getServerName(req);


        TraceContextManager.setContext(ctx, ctx.threadId);
        return ctx;
    }



    public static Object startHttpFilter (Object req, Object res){
            System.out.println("Enter Filter");
            return null;
        }
    public static void endHttpService(){

        TraceContext ctx = TraceContextManager.getContext();

        if (ctx == null) {
            return;
        }
        ctx.endTime = System.currentTimeMillis();
        ctx.endCpu = SysJMX.getCurrentThreadCPU();
        ctx.lastestTime = ctx.endTime - ctx.startTime;
        ctx.latestCpu = ctx.endCpu - ctx.startCpu;

        Class clazz = ctx._res.getClass();
        try {
            Method contentType = clazz.getDeclaredMethod("getContentType");
            ctx.http_content_type = String.valueOf(contentType.invoke(ctx._res));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        /*
        * Connection pool 제작하기
        *
        * */
        Connection con = Config.getInstance().sqlLogin();
        String query = "";

        query = "insert into transaction (txid, ResponseTime, CpuTime, SqlTime, serviceName ,Error) values(?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement pstmt = con.prepareStatement(query)) {
//            pstmt.setString(1, String.valueOf(ctx.txid));
//            pstmt.setLong(2, ctx.lastestTime);
//            pstmt.setLong(3, ctx.latestCpu);
//            pstmt.setLong(4, ctx.sqlTime);
//            pstmt.setString(5, ctx.serviceName);
//            pstmt.setInt(6, ctx.error);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        ctx.enTime = Timestamp.valueOf(LocalDateTime.now());
        query = "insert into transactionprofile (tPxid, startTime, collectTime, endTime, CpuTime ,ResponseTime, serviceName, remoteIp, " +
                "error, http_method, http_query, http_content_type, sqlCount, sqlTime, sqlText) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = con.prepareStatement(query)){
            pstmt.setString(1, String.valueOf(ctx.txid+1));
            pstmt.setTimestamp(2, ctx.stTime);
            pstmt.setTimestamp(3, ctx.collectTime);
            pstmt.setTimestamp(4, ctx.enTime);
            pstmt.setLong(5, ctx.latestCpu);
            pstmt.setLong(6, ctx.lastestTime);
            pstmt.setString(7, ctx.serviceName);
            pstmt.setString(8, ctx.remoteIp);
            pstmt.setInt(9, ctx.error);
            pstmt.setString(10, ctx.http_method);
            pstmt.setString(11, ctx.http_query);
            pstmt.setString(12, ctx.http_content_type);
            pstmt.setInt(13, ctx.sqlCount);
            pstmt.setLong(14, ctx.sqlTime);
            pstmt.setString(15, ctx.sqltext);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        Config.getInstance().insertTx(String.valueOf(ctx.txid),ctx.lastestTime,ctx.latestCpu, ctx.sqlTime, ctx.serviceName, ctx.remoteIp,  ctx.error);

        TraceContextManager.clearContext(ctx);

    }

}

