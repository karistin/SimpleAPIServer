package agent;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : agent
 * fileName       : Timeholder
 * author         : lucas
 * date           : 2022-07-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-22        lucas       최초 생성
 */
public class TimeHolder {
    private static Map<String, Long> timeCache = new HashMap<>();
    public static void start(String method) {
        timeCache.put(method, System.currentTimeMillis());
    }
    public static long cost(String method) {
        return System.currentTimeMillis() - timeCache.get(method);
    }
}
