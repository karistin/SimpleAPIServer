package Com.Agent;

import Com.Entity.MethodInstr;

import java.util.*;

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
    private static Map<String , MethodInstr> methodInstrList = new HashMap<>();

    private static Long time = 0L;

    public static Map<String, MethodInstr> getMethodInstrList() {
        return methodInstrList;
    }
//    static 메소드떔시 static
    public static void start(){
        time = System.currentTimeMillis();
    }

    public static void end(String packageName, String methodName){
        String className = packageName.substring(packageName.lastIndexOf("/")+1);
        packageName = packageName.substring(0,packageName.length()-className.length());

        MethodInstr methodInstr = new MethodInstr();
        Long temp = System.currentTimeMillis() - time;

        if (methodInstrList.containsKey(methodName))
        {
            methodInstr = methodInstrList.get(methodName);
            if (methodInstr.getClassName().equals(className) && methodInstr.getPackageName().equals(packageName))
            {
                methodInstr.plusCalls();
                methodInstr.setTotalTime(temp+methodInstr.getTotalTime());
                methodInstr.setSecond(methodInstr.getTotalTime()/methodInstr.getCalls());
                methodInstr.setClassName(className);
                methodInstr.setPackageName(packageName);
                methodInstrList.replace(methodName,methodInstr);
            }
        }
        else {
            methodInstr.setMethodName(methodName);
            methodInstr.setSecond(temp);
            methodInstr.setTotalTime(temp);
            methodInstr.setClassName(className);
            methodInstr.setPackageName(packageName);
            methodInstrList.put(methodName, methodInstr);
        }
//        System.out.println(methodInstr.getMethodName()+": "+methodInstr.getCultivTime());
    }
}
