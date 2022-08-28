package Com.Agent;

import Com.Entity.MethodInstr;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;

import static Com.Agent.App.LOG;

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

    public static Map<String, MethodInstr> getMethodInstrList() {
        return methodInstrList;
    }

    //    static 메소드떔시 static
    private static Long micortime = 0L;
    private static Long nanotime = 0L;
    private static Long cputime = 0L;
    private static ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();

    public static void start(){
        if(!mxBean.isCurrentThreadCpuTimeSupported())
        {
            LOG.info("Not Supported");
        }else {
            mxBean.setThreadCpuTimeEnabled(true);
        }
        micortime = System.currentTimeMillis();
        nanotime = System.nanoTime();
        cputime = mxBean.getCurrentThreadUserTime();
    }

    public static void end(String packageName, String methodName){
        Long mirco = System.currentTimeMillis() - micortime;
        Long nano = System.nanoTime() - nanotime;
        Long cpu = mxBean.getCurrentThreadUserTime() - cputime;
        if (cpu < 0) {
            cpu = Long.valueOf(0);
        }
        String className = packageName.substring(packageName.lastIndexOf("/")+1);
        packageName = packageName.substring(0,packageName.length()-className.length());

        MethodInstr methodInstr = new MethodInstr();


        if (methodInstrList.containsKey(methodName))
        {
            methodInstr = methodInstrList.get(methodName);
            if (methodInstr.getClassName().equals(className) && methodInstr.getPackageName().equals(packageName))
            {
                methodInstr.plusCalls();
                methodInstr.setTotalTime(mirco+methodInstr.getTotalTime());
                methodInstr.setSecond(methodInstr.getTotalTime()/methodInstr.getCalls());
                methodInstr.setLastTime(nano);
                methodInstr.setCputime(cpu);
                methodInstr.setTotalNanoTime(nano+methodInstr.getTotalNanoTime());
                methodInstr.setNanotime(methodInstr.getTotalNanoTime()/methodInstr.getCalls());

//                methodInstr.setSecond(temp);
                methodInstr.setClassName(className);
                methodInstr.setPackageName(packageName);
                methodInstrList.replace(methodName,methodInstr);
            }

        }
        else {
            methodInstr.setStacks((new Throwable()).getStackTrace());
            methodInstr.setMethodName(methodName);
            methodInstr.setSecond(mirco);
            methodInstr.setCputime(cpu);
            methodInstr.setLastTime(nano);
            methodInstr.setTotalNanoTime(nano);
            methodInstr.setNanotime(nano);
            methodInstr.setTotalTime(mirco);
            methodInstr.setClassName(className);
            methodInstr.setPackageName(packageName);
            methodInstrList.put(methodName, methodInstr);
        }
//        System.out.println(methodInstr.getMethodName()+": "+methodInstr.getCultivTime());
    }
}
