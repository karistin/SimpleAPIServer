package Com.Agent;

import Com.Entity.MethodInstr;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;

/**
 * packageName    : Agent
 * fileName       : MethodCount
 * author         : lucas
 * date           : 2022-07-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-26        lucas       최초 생성
 */
public class MethodCount {
    private static Map<String , MethodInstr> methodInstrList = new HashMap<>();

    private static Long time = 0L;

    public static Map<String, MethodInstr> getMethodInstrList() {
        return methodInstrList;
    }
//    static 메소드떔시 static
    private static Long instrTime = 0L;
    private static Long base_cpu = 0L;
    private static ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();

    public static void start(){
//        if(!mxBean.isCurrentThreadCpuTimeSupported())
//        {
//            LOG.info("Not Supported");
//        }else {
//            mxBean.setThreadCpuTimeEnabled(true);
//        }
//        time = System.nanoTime();
        time = System.currentTimeMillis();
//        base_cpu = mxBean.getCurrentThreadUserTime();
    }

    public static void end(String packageName, String methodName){
        Long temp = System.currentTimeMillis() - time;

        String className = packageName.substring(packageName.lastIndexOf("/")+1);
        packageName = packageName.substring(0,packageName.length()-className.length());

        MethodInstr methodInstr = new MethodInstr();
//        temp = ( mxBean.getCurrentThreadUserTime() - base_cpu)/temp;
//        temp =  mxBean.getCurrentThreadUserTime();
//        temp = (mxBean.getCurrentThreadUserTime() - base_cpu)/temp;
//        System.out.println(mxBean.getThreadCount());
        if (methodInstrList.containsKey(methodName))
        {
            methodInstr = methodInstrList.get(methodName);
            if (methodInstr.getClassName().equals(className) && methodInstr.getPackageName().equals(packageName))
            {
                methodInstr.plusCalls();
                methodInstr.setTotalTime(temp+methodInstr.getTotalTime());
                methodInstr.setSecond(methodInstr.getTotalTime()/methodInstr.getCalls());
//                methodInstr.setSecond(temp);
                methodInstr.setClassName(className);
                methodInstr.setPackageName(packageName);
                methodInstrList.replace(methodName,methodInstr);
            }

        }
        else {
            methodInstr.setStacks((new Throwable()).getStackTrace());
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
