package lucas.base.servlet;

/**
 * packageName    : lucas.base.servlet
 * fileName       : HttpServletMethodName
 * author         : lucas
 * date           : 2022-10-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-05        lucas       최초 생성
 */
public class HttpServletMethodName {
    private static final String[] methods = {"service", "doHead","doPost","doPut","doDelete","doOptions","doTrace","doGet"};

    public static boolean isHttpServletMethod(String methodName) {
        for (String method : methods) {
            if(method.equals(methodName))
            {
                return true;
            }
        }
        return false;
    }
}
