package lucas.base.trace;

import lucas.base.proxy.IHttpTrace;

import java.sql.Timestamp;

/**
 * packageName    : lucas.base.trace
 * fileName       : TraceContext
 * author         : lucas
 * date           : 2022-10-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-17        lucas       최초 생성
 */
public class TraceContext {

    protected TraceContext() {
    }
    public Object _req;
    public Object _res;
    public IHttpTrace http;

    public long txid;
    public long startTime;
    public long endTime;
    public Timestamp collectTime;
    public Timestamp stTime;
    public  Timestamp enTime;

    public Thread thread;
    public long threadId;
    public String serverName;
    public long startCpu;
    public long latestCpu;
    public long lastestTime;

    public long endCpu;
    public String serviceName;
    public String remoteIp;
    public String threadName;

    public int error;

    public String http_method;
    public String http_query;
    public String http_content_type;

    // sql
    public int sqlCount;
    public int sqlTime;
    public String sqltext;

    public void txString()
    {
        System.out.println(txid);
        System.out.println("Response Time : "+lastestTime);
        System.out.println("Cpu Time : "+latestCpu);
        System.out.println("serviceName : "+serviceName);
        System.out.println("Server IP : "+remoteIp);
        System.out.println("========================================");
    }
}
