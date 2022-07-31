package Agent;

import Entity.MethodInstr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private static List<MethodInstr> methodInstrList = new ArrayList<>();

    private static Long time = 0L;

    public static List<MethodInstr> getMethodInstrList() {
        return methodInstrList;
    }
    public static void start(String methodName){
        time = System.currentTimeMillis();
    }

    public static void end(String methodName){
        Long temp = System.currentTimeMillis() - time;
        MethodInstr methodInstr = new MethodInstr();
        methodInstr.setMethodName(methodName);
        methodInstr.plusCallCount();
        methodInstr.setCallTime(temp);
        methodInstr.setCultivTime(temp+methodInstr.getCallTime());
        methodInstrList.add(methodInstr);
//        System.out.println(methodInstrList.size());
//        methodInstr.reset();
    }
}
