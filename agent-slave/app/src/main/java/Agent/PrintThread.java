package Agent;

import Entity.MethodInstr;
import Util.LogFormatter;
import Util.MultiColumnPrinter;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.util.*;
import java.util.Map.Entry;
public class PrintThread extends Thread{
    boolean flags = false;

    SignalHandler signalHandler = new SignalHandler() {
        @Override
        public void handle(Signal sig) {
            flags = true;
        }
    };

    @Override
    public void run() {
        while (true) {
            Signal.handle(new Signal("INT"),signalHandler);
            MultiColumnPrinter printer = new MultiColumnPrinter(6, 8 ,"*",0,false) {
                @Override
                public void doPrint(String str) {
                    System.out.print(str);
                }

                @Override
                public void doPrintln(String str) {
                    System.out.println("");
                }
            };
            System.out.println("Sorting by Average CallTime");
            String titleRow[] = new String[6];
            titleRow[0] = "Method Name";
            titleRow[1] = "ClassName";
            titleRow[2] = "PackageName";
            titleRow[3] = "Count";
            titleRow[4] = "Average CallTime(ms)";
            titleRow[5] = "CultivTime(ms)";

            printer.addTitle(titleRow);
            Map<String, MethodInstr> methodInstrList = MyBCIMethod.getMethodInstrList();

            List<String> keyList = new ArrayList<>(methodInstrList.keySet());
            keyList.sort((Comparator.comparing(o -> methodInstrList.get(o).getCallTime())).reversed());

            for (String key : keyList) {
                String row[] = new String[6];
                MethodInstr methodInstr = methodInstrList.get(key);
                row[0] = methodInstr.getMethodName();
                row[1] = methodInstr.getClassName();
                row[2] = methodInstr.getPackageName();
                row[3] = String.valueOf(methodInstr.getCallCount());
                row[4] = methodInstr.getCallTime()+"ms";
                row[5] = methodInstr.getCultivTime()+"ms";

                printer.add(row);
            }

            printer.print();

            if (this.flags)
            {
                System.out.println("*".repeat(54));
                for( String name : App.taskRepository.classList())
                {
                    System.out.println(LogFormatter.ANSI_BLUE +name+LogFormatter.ANSI_WHITE);
                }

                System.out.println("\r\nWriting ClassName(write skip will be auto skip)(exit to end program)\r\n");
                Scanner sc = new Scanner(System.in);
                String className = sc.next();

                if (App.taskRepository.getClass(className).isPresent())
                {
                    App.taskRepository.getClass(className).get().printDataset();
                    System.out.println("\r\nWrite Any Word to Back\r\n");
                    sc.next();
                    continue;
                }
                else if(className.equals("skip")){
                    this.flags = false;
                }else if(className.equals("exit")){
                    System.exit(0);
                }
            }

            try {
                Thread.sleep(2000);
                System.out.print("Everything on the console will cleared");
                System.out.print("\033[H\033[2J");
                System.out.flush();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
