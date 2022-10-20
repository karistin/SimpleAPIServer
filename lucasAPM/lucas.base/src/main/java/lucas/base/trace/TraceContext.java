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
    protected TraceContext() {
    }

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
}
