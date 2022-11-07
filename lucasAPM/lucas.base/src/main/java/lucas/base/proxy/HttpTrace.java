package lucas.base.proxy;

import lucas.base.trace.TraceContext;

import javax.servlet.http.Cookie;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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
 *
 * org.apache.catalina.connector.RequestFacade 가정
 */
public class HttpTrace implements IHttpTrace{
    Class<?> clazz = null;

    Method serverName = null;
    Method requestURI = null;
    Method requestId = null;
    Method remoteAddr = null;
    Method method = null;
    Method queryString = null;
    Method attr = null;
    Method parameter = null;
    Method contentType = null;

    public HttpTrace(Object req) {
        try {
            clazz = req.getClass();
            Method[] meths = clazz.getDeclaredMethods();

//            System.out.println( "[ "+LocalDateTime.now() + " ]"+ Thread.currentThread().getName()+" : "+clazz.getName());
//            for (Method me : meths) {
//                System.out.println(me.getName());
//            }

            requestURI = clazz.getDeclaredMethod("getRequestURI");
            requestURI.setAccessible(true);

            requestId = clazz.getDeclaredMethod("getRequestedSessionId");
            requestId.setAccessible(true);

            remoteAddr = clazz.getDeclaredMethod("getRemoteAddr");
            remoteAddr.setAccessible(true);

            method = clazz.getDeclaredMethod("getMethod");
            method.setAccessible(true);

            queryString = clazz.getDeclaredMethod("getQueryString");
            queryString.setAccessible(true);


            serverName = clazz.getDeclaredMethod("getServerName");
            contentType = clazz.getDeclaredMethod("getContentType");


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
    public String getServerName(Object req) {
        try {
            return (String) serverName.invoke(req);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
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
        try {
            return String.valueOf(requestURI.invoke(req));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getRequestId(Object req) {
        return null;
    }

    @Override
    public String getRemoteAddr(Object req) {
        try {
            return String.valueOf(remoteAddr.invoke(req));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getMethod(Object req) {
        try {
            return (String) method.invoke(req);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getQueryString(Object req) {
        try {
            return (String) queryString.invoke(req);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getContentType(Object req) {
        try {
            return (String) contentType.invoke(req);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
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
