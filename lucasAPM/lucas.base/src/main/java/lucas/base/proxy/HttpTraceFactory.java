package lucas.base.proxy;

import lucas.base.trace.TraceContext;

import java.util.Enumeration;

/**
 * packageName    : lucas.base.proxy
 * fileName       : HttpTraceFactory
 * author         : lucas
 * date           : 2022-10-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-17        lucas       최초 생성
 */
public class HttpTraceFactory {
    public static final IHttpTrace dummy = new IHttpTrace() {
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
            return null;
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
    };


//    req.getClass().getClassLoader(), req
    public static IHttpTrace create(Object oReq) {


        return new HttpTrace(oReq);
//        try {
//
//            ClassLoader loader = LoaderManager.getHttpLoader(parent);
//
//
//
//            if (loader == null) {
//                return dummy;
//            }
//
//            Class c = null;
//
//            boolean reactive = true;
//            try {
//                Method m = oReq.getClass().getMethod("mutate");
//                c = Class.forName(HTTP_TRACE_WEBFLUX, true, loader);
//
//            } catch (Exception e) {
//                reactive = false;
//            }
//
//            if (!reactive) {
//                boolean servlet3 = true;
//                try {
//                    Method m = oReq.getClass().getMethod("logout");
//                } catch (Exception e) {
//                    servlet3 = false;
//                }
//
//                if(servlet3) {
//                    c = Class.forName(HTTP_TRACE3, true, loader);
//                } else {
//                    c = Class.forName(HTTP_TRACE, true, loader);
//                }
//            }
//
//            return (IHttpTrace) c.newInstance();
//        } catch (Throwable e) {
//            e.printStackTrace();
//            return dummy;
//        }
    }

}
