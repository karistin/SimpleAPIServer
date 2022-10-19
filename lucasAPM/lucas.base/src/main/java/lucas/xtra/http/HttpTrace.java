package lucas.xtra.http;

import lucas.base.proxy.IHttpTrace;
import lucas.base.trace.TraceContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * packageName    : lucas.xtra.http
 * fileName       : HttpTrace
 * author         : lucas
 * date           : 2022-10-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-19        lucas       최초 생성
 */
public class HttpTrace implements IHttpTrace {
    @Override
    public void start(TraceContext ctx, Object req, Object res) {

    }

    @Override
    public void end(TraceContext ctx, Object req, Object res) {

    }

    @Override
    public void rejectText(Object res, String text) {

    }

    @Override
    public void rejectUrl(Object res, String url) {

    }

    @Override
    public String getParameter(Object req, String key) {
        return null;
    }

    @Override
    public String getHeader(Object req, String key) {
        return null;
    }

    @Override
    public String getCookie(Object req, String key) {
        return null;
    }

    @Override
    public String getRequestURI(Object req) {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        if (uri == null)
            return "no-url";
        int x = uri.indexOf(';');
        if (x > 0)
            return uri.substring(0, x);
        else
            return uri;
    }

    @Override
    public String getRequestId(Object req) {
        return null;
    }

    @Override
    public String getRemoteAddr(Object req) {
        return null;
    }

    @Override
    public String getMethod(Object req) {
        return null;
    }

    @Override
    public String getQueryString(Object req) {
        return null;
    }

    @Override
    public String getContentType(Object req) {
        return null;
    }

    @Override
    public Object getAttribute(Object req, String key) {
        return null;
    }

    @Override
    public Enumeration getParameterNames(Object req) {
        return null;
    }

    @Override
    public Enumeration getHeaderNames(Object req) {
        return null;
    }
}
