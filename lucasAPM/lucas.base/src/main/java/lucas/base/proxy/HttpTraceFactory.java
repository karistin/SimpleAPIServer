package lucas.base.proxy;

import lucas.base.trace.TraceContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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

    private static final String HTTP_TRACE = "lucas.xtra.http.HttpTrace";
// not exist
    private static final String HTTP_TRACE3 = "lucas.xtra.http.HttpTrace3";


//    req.getClass().getClassLoader(), req
    public static IHttpTrace create(ClassLoader parent , Object oReq) {


        return new HttpTrace(oReq);
        /*
        * 클래스 로더를 동적으로 찾아와서 Class.forname으로 호출하기
        * 현제는 HttpTrace 에서 reflection 으로 불러옴
        *
        *
        *
        * */
//        try {

//            ClassLoader loader = LoaderManager.getHttpLoader(parent);

//            if (parent == null) {
//                return dummy;
//            }

//            Class c = null;
//
//            boolean reactive = false;
//            try {
//                Method m = oReq.getClass().getMethod("mutate");
//                c = Class.forName(HTTP_TRACE_WEBFLUX, true, loader);
//
//            } catch (Exception e) {
//                reactive = false;
//            }

//            if (!reactive) {
//                boolean servlet3 = true;
//                try {
//                    Method m = oReq.getClass().getMethod("logout");
//                } catch (Exception e) {
//                    servlet3 = false;
//                }
//
//                if(servlet3) {
////                    c = Class.forName(HTTP_TRACE3, true, parent);
//                    c = Class.forName(HTTP_TRACE, true, parent);
//                } else {
//                    c = Class.forName(HTTP_TRACE, true, parent);
//                }
//            }
//            Constructor constructor = c.getConstructor(null);
//            return (IHttpTrace) constructor.newInstance();
//        } catch (Throwable e) {
//            e.printStackTrace();
//            return dummy;
//        }
    }

}
