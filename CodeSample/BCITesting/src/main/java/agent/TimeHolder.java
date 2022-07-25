package agent;

import Entity.DataEntity;
import Repository.MemoryRepositoy;

import java.io.*;
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
    public static Map<String, Long> getHash() { return timeCache; }

    public static void start(String method) {
//        String method = "";
        if (method.contains("."))
        {
            method = method.substring(method.lastIndexOf(".")+1);
        }


        System.out.println(HelloAgent.memoryRepositoys.size());


        if (timeCache.containsKey(method)) {
            Long dat = timeCache.get(method)+1;
            timeCache.replace(method, dat);
        } else {
            timeCache.put(method, 1L);
        }
    }
    public static long cost(String method) {
        if (method.contains("."))
        {
            method = method.substring(method.lastIndexOf(".")+1);
        }
        System.out.println(timeCache);



        return timeCache.get(method);
//        timeCache.put(method, System.currentTimeMillis() - timeCache.get(method));
    }


}
