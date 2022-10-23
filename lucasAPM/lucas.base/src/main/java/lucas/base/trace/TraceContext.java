package lucas.base.trace;

import lucas.base.proxy.IHttpTrace;

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
    public boolean endHttpProcessingStarted = false;

    protected TraceContext() {
    }
    public enum GetBy {
        ThreadLocal,
        ThreadLocalTxid,
        ThreadLocalTxidByCoroutine,
        CoroutineLocal
    }
    public GetBy getBy;
    public Object _req;
    public Object _res;
    public IHttpTrace http;

    public TraceContext parent;
    public long txid;
    public String ctxid;
    public Thread thread;
    public long threadId;
    public long gxid;


    // profile Temp
//    public IProfileCollector profile;
//    public int profileCount;
//    public int profileSize;

    public long startTime;
    public long startCpu;
    public long latestCpu;

    public long bytes;
    public long latestBytes;
    public int status;

//    // service
//    public byte xType;
//    public XLogDiscardTypes.XLogDiscard discardType;

    public int serviceHash;
    public String serviceName;
    public String remoteIp;
    public String threadName;

    public int error;
    //public boolean done_http_service;
    public String http_method;
    public String http_query;
    public String http_content_type;

    // sql
    public int sqlCount;
    public int sqlTime;
    public String sqltext;

    @Override
    public String toString() {
        return "TraceContext{" +
                "endHttpProcessingStarted=" + endHttpProcessingStarted +
                ", getBy=" + getBy +
                ", _req=" + _req +
                ", _res=" + _res +
                ", http=" + http +
                ", parent=" + parent +
                ", txid=" + txid +
                ", ctxid='" + ctxid + '\'' +
                ", thread=" + thread +
                ", threadId=" + threadId +
                ", gxid=" + gxid +
                ", startTime=" + startTime +
                ", startCpu=" + startCpu +
                ", latestCpu=" + latestCpu +
                ", bytes=" + bytes +
                ", latestBytes=" + latestBytes +
                ", status=" + status +
                ", serviceHash=" + serviceHash +
                ", serviceName='" + serviceName + '\'' +
                ", remoteIp='" + remoteIp + '\'' +
                ", threadName='" + threadName + '\'' +
                ", error=" + error +
                ", http_method='" + http_method + '\'' +
                ", http_query='" + http_query + '\'' +
                ", http_content_type='" + http_content_type + '\'' +
                ", sqlCount=" + sqlCount +
                ", sqlTime=" + sqlTime +
                ", sqltext='" + sqltext + '\'' +
                '}';
    }
}
