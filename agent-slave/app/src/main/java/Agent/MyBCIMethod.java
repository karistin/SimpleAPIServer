package Agent;

import java.util.HashMap;
import java.util.Map;

import static Agent.App.LOG;

/**
 * packageName    : Agent
 * fileName       : MyBCIMethod
 * author         : lucas
 * date           : 2022-07-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-26        lucas       최초 생성
 */
public class MyBCIMethod {
    private static final Map<String, Long> callCount = new HashMap<>();
    private static final Map<String, Long> callTime = new HashMap<>();
    private static long time = 0L;

    public static Map<String, Long> getCallTime() {
        return callTime;
    }

    public static Map<String, Long> getCount() {
        return callCount;
    }

    public static void setCallTime(String methodName, Long time) {
        callTime.put(methodName, time);
    }
    public static void setCount(String methodName, Long count) {
        callCount.put(methodName, count);
    }

    public static void start(String methodName){
        callCount.replace(methodName,callCount.get(methodName)+1);
        time = System.currentTimeMillis();
    }
    public static void end(String methodName){
//        LOG.info(methodName + ": "+callCount.get(methodName));
        callTime.put(methodName, callTime.get(methodName)+(System.currentTimeMillis()-time));
        LOG.info(methodName +": "+callTime.get(methodName) +"ms");
    }
}
