package lucas.base.proxy;

import lucas.base.trace.TraceContext;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

/**
 * packageName    : lucas.base.proxy
 * fileName       : IHttpTrace
 * author         : lucas
 * date           : 2022-10-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-17        lucas       최초 생성
 */
public interface IHttpTrace {
    void start(TraceContext ctx, Object req, Object res);

    void end(TraceContext ctx, Object req, Object res);

    void rejectText(Object res, String text);

    void rejectUrl(Object res, String url);

    String getParameter(Object req, String key);
    String getHeader(Object req, String key);
    String getCookie(Object req, String key);
    String getRequestURI(Object req);
    String getRequestId(Object req);
    String getRemoteAddr(Object req);
    String getMethod(Object req);
    String getQueryString(Object req);

    String getContentType(Object req);
    Object getAttribute(Object req, String key);
    Enumeration getParameterNames(Object req);
    Enumeration getHeaderNames(Object req);
}
