package Com.Agent.Print;

import Com.Agent.App;
import Com.Agent.MyBCIMethod;
import Com.Entity.MethodInstr;
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

    SignalHandler signalHandler = new SignalHandler() {
        @Override
        public void handle(Signal sig) {
            state = menuState.MENU;
        }
    };
    enum menuState{
        METHOD,SUMMARY,EVENT,PERFORMACE,CLASS,MENU
    }
    @Override
    public void run() {

        Signal.handle(new Signal("INT"),signalHandler);
        Scanner sc = new Scanner(System.in);
        List<String> index = null;

        while (true) {


            if (state == menuState.METHOD)
            {
                int col = 4;
                int gap = 10;
                int printdepth = 20;


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
                titleRow[2] = "second(ms)";
                titleRow[3] = "TotalTime(ms)";
                printer.addTitle(titleRow);
//                sorting by CallTime
                Map<String, MethodInstr> methodInstrList = MyBCIMethod.getMethodInstrList();
                List<String> keyList = new ArrayList<>(methodInstrList.keySet());
                keyList.sort((Comparator.comparing(o -> methodInstrList.get(o).getSecond())).reversed());
                if (keyList.size() > printdepth)
                {
                    keyList = new ArrayList<>(keyList.subList(0,printdepth));
                }


                for (String key : keyList) {
                    String[] row = new String[4];
                    MethodInstr methodInstr = methodInstrList.get(key);
                    row[0] = methodInstr.getPackageName()+methodInstr.getClassName()+"/"+methodInstr.getMethodName();
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
            else if (state == menuState.MENU)
            {
                while(true)
                {
                    System.out.println("AgentSlve Menu");
                    System.out.println("1. MethodProfiling");
                    System.out.println("2. SearchClssinfo");
                    System.out.println("3. EventLog");
                    System.out.println("4. JVM Summary");
                    System.out.println("5. Performance");
                    System.out.print("6. Exit\r\n=>");
                    String asn = sc.next();
                    switch (asn){
                        case "1":
                            state = menuState.METHOD;
                            break;
                        case "2":
                            state = menuState.CLASS;
                            break;
                        case "3":
                            state = menuState.EVENT;
                            break;
                        case "4":
                            state = menuState.SUMMARY;
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
            else if (state == menuState.CLASS)
            {
                if (index==null)
                {
                    for( String name : App.taskRepository.classList())
                    {
                        System.out.println(LogFormatter.ANSI_BLUE +name+LogFormatter.ANSI_WHITE);
                    }

                }else{
                    for( String name : App.taskRepository.classList())
                    {
                        for (String ind :index)
                        {
                            if (name.equals(ind))
                            {
                                System.out.println(LogFormatter.ANSI_BLUE +name+LogFormatter.ANSI_WHITE);
                            }
                        }
                    }
                }

                System.out.println("\r\nClassName List(exit go to menu)\r\n");
                String className = sc.nextLine();
                if (className.contains("grep"))
                {
                    index = List.of((className.split(" ")));

                }
                else if (App.taskRepository.getClass(className).isPresent())
                {
                    App.taskRepository.getClass(className).get().printDataset();
                    System.out.println("\r\nContinue to Enter\r\n");
                    sc.nextLine();

                } else if(className.equals("exit"))
                {
                    index.clear();
                    state = menuState.MENU;
                }
            }
            else if (state == menuState.EVENT)
            {
                try(BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/agentLog.txt"))){
                    String line;
                    while ((line = reader.readLine()) != null){
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("\r\nContinue to Enter\r\n");
                sc.nextLine();
                state = menuState.MENU;
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
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
}
