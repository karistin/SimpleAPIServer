package lucas.base.trace;

/**
 * packageName    : lucas.base.trace
 * fileName       : TraceMain
 * author         : lucas
 * date           : 2022-10-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-17        lucas       최초 생성
 */
public class TraceMain {
    public static Object startHttpService(Object req, Object res) {
        System.out.println(Thread.currentThread().getName()+" Enter start");
        return null;
    }

        public static Object startHttpFilter (Object req, Object res){
            System.out.println("Enter Filter");
            return null;
        }
    public static void endHttpService(){
        System.out.println(Thread.currentThread().getName()+"End");
        StackTraceElement[] e = Thread.currentThread().getStackTrace();
//        for (StackTraceElement els: e) {
//            System.out.println(els.getClassName() + " : "+els.getMethodName());
//        }
    }

}

