package lucas.agent;

/**
 * packageName    : lucas.agent
 * fileName       : MethodHook
 * author         : lucas
 * date           : 2022-09-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-16        lucas       최초 생성
 */
public class MethodHook {

    private static Long time = 0L;

    public static void starthook(String method){
        System.out.println(method + "out");
        time = System.currentTimeMillis();
    }

    public static void endhook(){
        System.out.println("Time Spend : "+(System.currentTimeMillis()-time));
    }
}
