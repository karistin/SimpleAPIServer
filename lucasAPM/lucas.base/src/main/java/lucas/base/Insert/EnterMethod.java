package lucas.base.Insert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * packageName    : lucas.base.Insert
 * fileName       : EnterMethod
 * author         : lucas
 * date           : 2022-10-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-05        lucas       최초 생성
 */
public class EnterMethod {

    private static Long time = 0L;
    public static void enterServlet(Object req, Object resp) throws NoSuchMethodException {

        Class<?> clazz = req.getClass();


//        Method[] clazzMethods = clazz.getDeclaredMethods();
//        for(Method clazzMethod: clazzMethods)
//        {
//            System.out.println(clazzMethod.getName() +" : " );
//
//        }


        Method URI = (clazz.getDeclaredMethod("getRequestURI"));

//        Method Header = (clazz.getDeclaredMethod("getHeaders"));
        Method ContentType = (clazz.getDeclaredMethod("getContentType"));
        Method methods = (clazz.getDeclaredMethod("getMethod"));
//        Method cookie = clazz.getDeclaredMethod()
//        Method attribute = (clazz.getDeclaredMethod("getAttribute"));
//        Method parameterMap = (clazz.getDeclaredMethod("getParameterMap"));



        try {
            System.out.println(URI.getName() +" : " + URI.invoke(req));
//            System.out.println(Header.getName() +" : " + Header.invoke(req));
            System.out.println(ContentType.getName() +" : " + ContentType.invoke(req));
            System.out.println(methods.getName() +" : " + methods.invoke(req));
//            System.out.println(attribute.getName() +" : " + attribute.invoke(req));
//            System.out.println(parameterMap.getName() +" : " + parameterMap.invoke(req));


        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void enterTime()
    {
        time = System.currentTimeMillis();
    }
    public static void endServlet()
    {
        System.out.println(System.currentTimeMillis() - time +"ms");
    }
}
