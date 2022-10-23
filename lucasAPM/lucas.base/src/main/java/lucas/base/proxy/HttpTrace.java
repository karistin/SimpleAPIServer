package lucas.base.proxy;

import lucas.base.trace.TraceContext;

import javax.servlet.http.Cookie;
import java.lang.reflect.Constructor;
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
    Method contentType = null;
    String methodString = "";
    String address = "";
    String contenType = "";

    String uri = "";

    public HttpTrace(Object req) {
        try {
            clazz = req.getClass();
//            Constructor<?> constructor = clazz.getConstructor();
//            object = constructor.newInstance();
//
//            parmeter = object.getClass().getDeclaredMethod("getParameter",String.class);
//            header = object.getClass().getDeclaredMethod("getHeader",String.class);
//            cookie = object.getClass().getDeclaredMethod("getCookies");
            requestURI = clazz.getDeclaredMethod("getRequestURI");
            uri = (String) requestURI.invoke(req);
//            requestId = object.getClass().getDeclaredMethod("getRequestedSessionId");
////            requestId = clazz.getDeclaredMethod("getRequestId");
            remoteAddr = clazz.getDeclaredMethod("getRemoteAddr");
            address = (String) remoteAddr.invoke(req);
            method = clazz.getDeclaredMethod("getMethod");
            methodString = (String) method.invoke(req);
//            queryString = object.getClass().getDeclaredMethod("getQueryString");
//            attr = object.getClass().getDeclaredMethod("getAttribute", String.class);
//            parameter = object.getClass().getDeclaredMethod("getParameter", String.class);
            contentType = (clazz.getDeclaredMethod("getContentType"));
            contenType = (String) contentType.invoke(req);
        } catch (Exception e) {
            System.out.println("["+this.getClass().getName()+"]"+"Reflection Error");
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
        return null;
    }

    @Override
    public String getCookie(Object req, String key) {
        return null;
    }

    @Override
    public String getRequestURI() {
        return uri;
    }

    @Override
    public String getRequestId() {
        return null;
    }

    @Override
    public String getRemoteAddr() {

        return address;
    }

    @Override
    public String getMethod() {
        return methodString;
    }

    @Override
    public String getQueryString() {
        return null;
    }

    @Override
    public String getContentType() {

        return contenType;
    }

    @Override
    public Object getAttribute(String key) {
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
