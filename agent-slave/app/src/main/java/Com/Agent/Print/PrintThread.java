package Com.Agent.Print;

import Com.Agent.App;
import Com.Agent.MyBCIMethod;
import Com.Entity.DataSet;
import Com.Entity.MethodInstr;
import Com.Entity.Methodvalue;
import Com.Util.LogFormatter;
import Com.Util.MultiColumnPrinter;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PrintThread extends Thread{

    menuState state = menuState.METHOD;
    int indexCount = 0;
    SignalHandler signalHandler = new SignalHandler() {
        @Override
        public void handle(Signal sig) {
            state = menuState.MENU;
        }
    };

    enum menuState{
        METHOD,SUMMARY,EVENT,PERFORMACE,CLASS,MENU,SEARCHING
    }

    private String indexing(String name, List<String>index){
        for(String ind: index)
        {
            if (name.contains(ind.toLowerCase()))
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

    @Override
    public void run() {

        Signal.handle(new Signal("INT"),signalHandler);
        Scanner sc = new Scanner(System.in);
        List<String> index = new ArrayList<>();
        int printdepth = 20;
        String indexing = "";
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

                System.out.println("Sorting by second");

                String[] titleRow = new String[col];
                titleRow[0] = "Method";
                titleRow[1] = "Call";
                titleRow[2] = "Average second(ms)";
                titleRow[3] = "TotalTime(ms)";
                printer.addTitle(titleRow);
//                sorting by CallTime
                Map<String, MethodInstr> methodInstrList = MyBCIMethod.getMethodInstrList();

                List<String> keyList = new ArrayList<>(methodInstrList.keySet());


                keyList.sort((Comparator.comparing(o -> methodInstrList.get(o).getTotalTime())).reversed());

//                 0 show all
                if (printdepth !=0) {
                    if (keyList.size() > printdepth) {
                        keyList = new ArrayList<>(keyList.subList(0, printdepth));
                    }
                }

                for (String key : keyList) {
                    String[] row = new String[col];
                    MethodInstr methodInstr = methodInstrList.get(key);
                    String name = methodInstr.getPackageName()+methodInstr.getClassName()+"/"+methodInstr.getMethodName();
                    if(!name.contains(indexing) && !indexing.equals("0"))
                        continue;
                    row[0] = name;
                    row[1] = String.valueOf(methodInstr.getCalls());
                    row[2] = methodInstr.getSecond()+"ms";
                    row[3] = methodInstr.getTotalTime()+"ms";

                    printer.add(row);
                }

                printer.print();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            else if(state ==menuState.SEARCHING)
            {
                Map<String, MethodInstr> methodInstrList = MyBCIMethod.getMethodInstrList();
                for(Map.Entry<String, MethodInstr> method: methodInstrList.entrySet())
                {
                    System.out.println(method.getKey());
                }
                System.out.println("\r\nInput Method Name\r\n");
                String name = sc.nextLine();
                if(name.equals("exit"))
                {
                    state = menuState.MENU;
                }
                else if (methodInstrList.get(name) != null)
                {
                    MethodInstr method =  methodInstrList.get(name);
                    String packageClass = method.getPackageName()+method.getClassName();
                    System.out.println("\r\n"+packageClass+'/'+method.getMethodName());
//                    System.out.println(method.getMethodName());

                    DataSet methodInstrs = App.taskRepository.getClass(packageClass).get();
                    ArrayList<Methodvalue> methodvalues = methodInstrs.getMethodvalues();
                    for(Methodvalue methodvalue:methodvalues)
                    {
//                        System.out.println(methodvalue.getName());
                        if (methodvalue.getName().equals(name))
                        {
                            methodvalue.printInsn();
                        }
                    }

                    System.out.println("=================================");
                    for(StackTraceElement stack: method.getStacks())
                    {
                        System.out.println(stack);
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
                System.out.println("Not Making");
                state = menuState.MENU;
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
                    System.out.println("AgentSlve Menu");
                    System.out.println("1. MethodProfiling");
                    System.out.println("2. SearchClssinfo");
                    System.out.println("3. EventLog");
                    System.out.println("4. Searching");
//                    System.out.println("5. JVM Summary");
                    System.out.println("5. Performance");
                    System.out.print("6. Exit\r\n=>");
                    String asn = sc.nextLine();
                    switch (asn){
                        case "1":
                            state = menuState.METHOD;
                            System.out.println("Input Depth");
                            printdepth = sc.nextInt();
                            System.out.println("Indexing");
                            indexing = sc.next();
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
                            state = menuState.PERFORMACE;
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
