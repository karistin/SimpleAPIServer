package lucas.base.proxy;

import lucas.base.trace.TraceContext;

import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * packageName    : lucas.base.proxy
 * fileName       : HttpTrace
 * author         : lucas
 * date           : 2022-10-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-17        lucas       최초 생성
 */
public class HttpTrace implements IHttpTrace{
    Class<?> clazz = null;
    Method parmeter = null;
    Method header = null;
    Method cookie = null;
    Method requestURI = null;
    Method requestId = null;
    Method remoteAddr = null;
    Method method = null;
    Method queryString = null;
    Method attr = null;
    Method parameter = null;

    public HttpTrace(Object req) {
        try {
            clazz = req.getClass();
            parmeter = clazz.getDeclaredMethod("getParameter",String.class);
            header = clazz.getDeclaredMethod("getHeader",String.class);
            requestURI = clazz.getDeclaredMethod("getRequestURI");
//            requestId = clazz.getDeclaredMethod("getRequestId");
            remoteAddr = clazz.getDeclaredMethod("getRemoteAddr");
            method = clazz.getDeclaredMethod("getMethod");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        try{
            return (String) parmeter.invoke(req,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getHeader(Object req, String key) {
        try{
            return (String) header.invoke(req,key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getCookie(Object req, String key) {
        return null;
    }

    @Override
    public String getRequestURI(Object req){
        try {
            String uri = (String) requestURI.invoke(req);
            if (uri == null) {
                return "NO-url";
            }
            int x = uri.indexOf(";");
            if (x > 0) {
                return uri.substring(0, x);
            } else {
                return uri;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getRequestId(Object req) {

        return null;
    }

    @Override
    public String getRemoteAddr(Object req) {
        try{
            return (String) remoteAddr.invoke(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getMethod(Object req) {
        try{
            return (String) method.invoke(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getQueryString(Object req) {
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
