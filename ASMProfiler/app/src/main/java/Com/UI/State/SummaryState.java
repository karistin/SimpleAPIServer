package Com.UI.State;

import Com.UI.Context;

import java.lang.management.*;
import java.util.Date;

/**
 * packageName    : Com.UI
 * fileName       : SummaryState
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public class SummaryState implements State{
    private static SummaryState singleton = new SummaryState();
    private SummaryState(){

    }


    public static State getInstance(){
        return singleton;
    }


    @Override
    public void showScreen(Context context) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean() ;
        MemoryUsage heapMemoryUsage = memBean.getHeapMemoryUsage();
        MemoryUsage nonheapMemoryUsage = memBean.getNonHeapMemoryUsage();

        long[] allThreadIds = threadMXBean.getAllThreadIds();
        long nano =0;
        for (long id : allThreadIds) {
            nano += threadMXBean.getThreadCpuTime(id);
        }

        System.out.println("Java Virtual Machine : " + System.getProperty("java.vm.name"));
        System.out.println("Vendor : " + System.getProperty("java.vm.specification.vendor"));
        System.out.println("PID : "+ProcessHandle.current().pid());
        System.out.println("Runtime : "+new Date(runtimeMXBean.getStartTime()));
        System.out.println("Uptime : "+runtimeMXBean.getUptime()/1000+" s");
        System.out.println("Total CPU time : "+nano/1E6);
        System.out.println("Working dir : "+System.getProperty("user.dir"));

        System.out.println("Agent log :" +System.getProperty("user.dir")+"/agentLog.txt");
        System.out.println("=====================================================");
        System.out.println("Limit Heap memory "+ heapMemoryUsage.getMax());
        System.out.println("Allocated Heap memory "+ heapMemoryUsage.getCommitted());
        System.out.println("Using Heap memory "+ heapMemoryUsage.getUsed());

        System.out.println("=======================================================");
        System.out.println("Limit NonHeap memory "+ nonheapMemoryUsage.getMax());
        System.out.println("Allocated NonHeap memory "+ nonheapMemoryUsage.getCommitted());
        System.out.println("Using NonHeap memory "+ nonheapMemoryUsage.getUsed());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
