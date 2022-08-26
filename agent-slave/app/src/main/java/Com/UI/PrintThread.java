package Com.UI;

import Com.Agent.App;
import Com.Agent.CostAccounter;
import Com.Agent.MethodCount;
import Com.Agent.MyClassFileTransformer;
import Com.Entity.*;
import Com.Util.Filter;
import Com.Util.LogFormatter;
import Com.Util.MultiColumnPrinter;
import org.barfuin.texttree.api.DefaultNode;
import org.barfuin.texttree.api.TextTree;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.*;
import java.util.*;

public class PrintThread extends Thread{

    menuState state = menuState.METHOD;
    int indexCount = 0;
    SignalHandler signalHandler = new SignalHandler() {
        @Override
        public void handle(Signal sig) {
            if(state != menuState.MENU)
                state = menuState.MENU;
            else{
                System.exit(0);
            }
        }
    };

    enum menuState{
        METHOD,SUMMARY,EVENT,PERFORMACE,CLASS,MENU,SEARCHING
    }

    private String indexing(String name, List<String>index){
        for(String ind: index)
        {
            if (name.toLowerCase().contains(ind.toLowerCase()))
            {
                name = name.replace(ind, LogFormatter.ANSI_BLUE+ind+LogFormatter.ANSI_WHITE);
                return name;
            }
        }
        return "-1";
    }
    private void printindexing(String name, List<String>index)
    {
        if (index.size() != 0)
        {
            name = indexing(name ,index);
            if(!name.equals("-1")){
                System.out.println(name);
                indexCount +=1;
            }
            return;
        }
        System.out.println(name);
        indexCount +=1;
    }

    private DataSet findDataSet(MethodInstr method)
    {
        String name = method.getPackageName()+method.getClassName();
        if(App.taskRepository.getClass(name).isPresent()) {
            return App.taskRepository.getClass(name).get();
        }
        return null;
    }

    private Methodvalue findMethod(DataSet dataSet, MethodInstr method)
    {
        for(Methodvalue methodvalue: dataSet.getMethodvalues())
        {
            if(methodvalue.getName().equals(method.getMethodName()))
            {
                return methodvalue;
            }
        }
        return null;
    }
    @Override
    public void run() {


        Signal.handle(new Signal("INT"),signalHandler);
        Scanner sc = new Scanner(System.in);
        List<String> index = new ArrayList<>();
        int printdepth = 20;
        String indexing = "";
        int sort = 2;

        while (true) {
            if (state == menuState.METHOD)
            {



                int col = 4;
                int gap = 10;
                MultiColumnPrinter printer = new MultiColumnPrinter(col, gap ,"*",0,false) {
                    @Override
                    public void doPrint(String str) {
                        System.out.print(str);
                    }

                    @Override
                    public void doPrintln(String str) {
                        System.out.println("");
                    }
                };

                String[] titleRow = new String[col];
                titleRow[0] = "Package.Classes."+LogFormatter.ANSI_RED+"Method"+LogFormatter.ANSI_WHITE;
                titleRow[1] = "Call";
                titleRow[2] = "Average second(ms)";
                titleRow[3] = "TotalTime(ms)";
                printer.addTitle(titleRow);
//                sorting by CallTime
                Map<String, MethodInstr> methodInstrList = MethodCount.getMethodInstrList();

                List<String> keyList = new ArrayList<>(methodInstrList.keySet());

                if (sort == 1)
                {
                    keyList.sort((Comparator.comparing(o -> methodInstrList.get(o).getCalls())).reversed());
                    System.out.println("Sorting by Call");
                }
                else if (sort == 2)
                {
                    keyList.sort((Comparator.comparing(o -> methodInstrList.get(o).getSecond())).reversed());
                    System.out.println("Sorting by Second");
                }
                else
                {
                    keyList.sort((Comparator.comparing(o -> methodInstrList.get(o).getTotalTime())).reversed());
                    System.out.println("Sorting by Totaltime");
                }

//                 0 show all
                if (printdepth !=0) {
                    if (keyList.size() > printdepth) {
                        keyList = new ArrayList<>(keyList.subList(0, printdepth));
                    }
                }
//                System.out.println(indexing);
//                Erroring

                for (String key : keyList) {
                    String[] row = new String[col];
                    MethodInstr methodInstr = methodInstrList.get(key);
                    String name = methodInstr.getPackageName()+methodInstr.getClassName()+"/"+LogFormatter.ANSI_RED+methodInstr.getMethodName()+LogFormatter.ANSI_WHITE;
//                    if(!name.contains(indexing) && !indexing.equals("0"))
//                        continue;
                    row[0] = name;
                    row[1] = String.valueOf(methodInstr.getCalls());
                    row[2] = methodInstr.getSecond()+"ms";
                    row[3] = methodInstr.getTotalTime()+"ms";

                    printer.add(row);
                }
                printer.print();

//                System.out.println(CostAccounter.getAllocationCost());

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(state == menuState.SEARCHING)
            {
                Map<String, MethodInstr> methodInstrList = MethodCount.getMethodInstrList();
                System.out.println(index);
                System.out.println("=================================");


                for(Map.Entry<String, MethodInstr> method: methodInstrList.entrySet())
                {
                    String name = method.getKey();
                    if(methodInstrList.get(name) != null)
                    {
                        MethodInstr methodInstr = methodInstrList.get(method.getKey());
                        printindexing(methodInstr.getClassName()+"."+LogFormatter.ANSI_RED+method.getKey()+LogFormatter.ANSI_WHITE, index);
                    }
                }
                System.out.println("=================================");

                System.out.println("\r\nInput Method Name\r\n");
                String name = sc.nextLine();

                if(name.equals("exit"))
                {
                    state = menuState.MENU;
                }
                else if(name.contains("grep"))
                {
                    index = List.of((name.split(" ")));
                    index = index.subList(1, index.size());
                }
                else if(name.startsWith("tree"))
                {
//                    System.out.println("tree");

                    List<String> nameList = List.of(name.split(" "));
                    name = nameList.get(nameList.size()-1);
                    System.out.println("==========================\r\n\r\n");

                    List<MethodInsnValue> methodInsnValueList = null;
                    if(methodInstrList.get(name) !=null)
                    {
                        MethodInstr method = methodInstrList.get(name);
                        DefaultNode tree = new DefaultNode(method.getClassName()+"."+LogFormatter.ANSI_RED+name+LogFormatter.ANSI_WHITE);
                        System.out.println(method.getPackageName() + method.getClassName() +"."+method.getMethodName()+"\r\n\r\n");
                        DataSet dataSet = findDataSet(method);

                        if(dataSet != null)
                        {
                            Methodvalue methodvalue = findMethod(dataSet, method);
                            if(methodvalue !=null)
                            {

                                methodInsnValueList = methodvalue.getMethodInsnValues();
                                for (MethodInsnValue methodInsnValue : methodInsnValueList) {
                                    DefaultNode node = new DefaultNode(methodInsnValue.getOwner()+"."+LogFormatter.ANSI_RED+methodInsnValue.getName()+LogFormatter.ANSI_WHITE);
                                    tree.addChild(node);

                                    if (methodInstrList.get(methodInsnValue.getName()) != null){
                                        MethodInstr method2 = methodInstrList.get(methodInsnValue.getName());
                                        DataSet dataSet2 = findDataSet(method2);
                                        if (dataSet2 != null) {

                                            Methodvalue methodvalue2 = findMethod(dataSet2, method2);
                                            if (methodvalue2 != null) {
                                                List<MethodInsnValue> methodInsnValueList2 = methodvalue2.getMethodInsnValues();
                                                for (MethodInsnValue methodInsnValue2 : methodInsnValueList2) {
                                                    DefaultNode servnode = new DefaultNode(methodInsnValue2.getOwner()+"."+LogFormatter.ANSI_RED+methodInsnValue2.getName()+LogFormatter.ANSI_WHITE);
                                                    node.addChild(servnode);

                                                    if(methodInstrList.get(methodInsnValue2.getName()) != null)
                                                    {
                                                        MethodInstr method3 = methodInstrList.get(methodInsnValue2.getName());
                                                        DataSet dataSet3 = findDataSet(method3);
                                                        if (dataSet3 != null) {
                                                            Methodvalue methodvalue3 = findMethod(dataSet3, method3);
                                                            if (methodvalue3 != null) {
                                                                List<MethodInsnValue> methodInsnValueList3 = methodvalue3.getMethodInsnValues();
                                                                for (MethodInsnValue methodInsnValue3 : methodInsnValueList3) {
                                                                    servnode.addChild(new DefaultNode(methodInsnValue3.getOwner()+"."+LogFormatter.ANSI_RED+methodInsnValue3.getName()+LogFormatter.ANSI_WHITE));
                                                                }
                                                            }
                                                        }
                                                    }



                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                        String renderd = TextTree.newInstance().render(tree);
                        System.out.println(renderd);
                        sc.nextLine();


                    }



                }
                else if (methodInstrList.get(name) != null)
                {
                    MethodInstr method =  methodInstrList.get(name);
                    DataSet dataSet = findDataSet(method);
                    System.out.println(method.getPackageName()+method.getClassName()+"."+method.getMethodName());
                    if(dataSet !=null)
                    {
                        Methodvalue methodvalue = findMethod(dataSet, method);
                        if (methodvalue != null) {

                            methodvalue.printInsn();
                        }
                    }



                    System.out.println("=================================");
                    StackTraceElement[] stack = method.getStacks();
                    for(int i=0; i<stack.length; i++)
                    {
                        if(i==0)
                            continue;

                        System.out.println("["+i+"] "+stack[i]);
                    }
                    System.out.println("=================================");
                    sc.nextLine();
                }
            }
            else if (state == menuState.CLASS)
            {
                for( String name : App.taskRepository.classList())
                {
                    printindexing(name, index);
                }
                System.out.println("Count : "+indexCount);
                indexCount = 0;

                System.out.println("\r\nClassName List(exit go to menu)\r\n");
                String className = sc.nextLine();

                if (className.contains("grep"))
                {
                    index = List.of((className.split(" ")));
                    index = index.subList(1, index.size());
                }
                else if (App.taskRepository.getClass(className).isPresent())
                {
                    App.taskRepository.getClass(className).get().printDataset();
                    System.out.println("\r\nContinue to Enter");
                    sc.nextLine();
                    index = new ArrayList<>();

                } else if(className.equals("exit"))
                {
                    state = menuState.MENU;
                }
            }
            else if (state == menuState.EVENT)
            {
                try(BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/agentLog.txt"))){
                    String line;
                    while ((line = reader.readLine()) != null){
                        printindexing(line, index);
                    }
                } catch (IOException e) {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
                System.out.println("Count : "+indexCount);
                indexCount = 0;

                System.out.println("\r\nLog List(exit go to menu)");
                String className = sc.nextLine();

                if (className.contains("grep"))
                {
                    index = List.of((className.split(" ")));
                    index = index.subList(1, index.size());
                }
                else if(className.equals("exit"))
                {
                    state = menuState.MENU;
                }
            }
            else if (state == menuState.SUMMARY)
            {
//                OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
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

                System.out.println("=======================================================");
                System.out.println("Currently loaded : " + App.instrument.getAllLoadedClasses().length);

                System.out.println("=======================================================");
                System.out.println("Currently live : ");
                System.out.println("Currently live daemons : ");
                System.out.println("Peak : ");
                System.out.println("Total started : ");


                System.out.println("=======================================================");
                System.out.println("GC Name : ");
                System.out.println("collections : ");
                System.out.println("Time : ");


                System.out.println("=======================================================");
                System.out.println("OS name : ");
                System.out.println("OS version : ");
                System.out.println("Architecture : ");
                System.out.println("Processors : ");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            else if (state == menuState.PERFORMACE)
            {
                System.out.println("Not Making");
                state = menuState.MENU;
            }
            else if (state == menuState.MENU)
            {
                while(true)
                {
                    index = new ArrayList<>();
                    indexCount = 0;
                    if (App.filteringName == null) {
                        System.out.println("Filter " + LogFormatter.ANSI_RED+Arrays.toString(Filter.getClassFilters())+LogFormatter.ANSI_WHITE);
                    } else {
                        System.out.println("Filter "+ LogFormatter.ANSI_RED+App.filteringName+LogFormatter.ANSI_WHITE);
                    }

                    System.out.println("AgentSlve Menu");
                    System.out.println("1. MethodProfiling");
                    System.out.println("2. SearchClssinfo");
                    System.out.println("3. EventLog");
                    System.out.println("4. Searching");
//                    System.out.println("5. JVM Summary");
                    System.out.println("5. JVM Summary");
                    System.out.print("6. Exit\r\n=>");
                    String asn = sc.nextLine();
                    switch (asn){
                        case "1":
                            state = menuState.METHOD;
                            System.out.println("Input Depth(zero to default)");
                            printdepth = sc.nextInt();
                            System.out.println("Indexing(zero to default)");
                            indexing = sc.next();
                            System.out.println("Sorting Method(default TotalTime)");
                            System.out.println("1 : Call \t 2 : Average Second \t 3 : Total Time");
                            sort = sc.nextInt();
                            break;
                        case "2":
                            state = menuState.CLASS;
                            break;
                        case "3":
                            state = menuState.EVENT;
                            break;
                        case "4":
                            state = menuState.SEARCHING;
                            break;
                        case "5":
                            state = menuState.SUMMARY;
                            break;
                        case "6":
                            System.exit(0);
                        default:
                    }
                    break;
                }
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

    }
}
