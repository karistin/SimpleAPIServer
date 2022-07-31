package Agent;

import Entity.MethodInstr;
import Util.MultiColumnPrinter;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
public class PrintThread extends Thread{
    @Override
    public void run() {
        while (true) {
//            List<Map.Entry<String, Long>> entryList = new LinkedList<>(callCount.entrySet());
//            entryList.sort(new Comparator<Map.Entry<String, Long>>() {
//                @Override
//                public int compare(Entry<String, Long> o1, Entry<String, Long> o2) {
//                    return (int) (o2.getValue() - o1.getValue());
//                }
//            });


            MultiColumnPrinter printer = new MultiColumnPrinter(4, 5 ,"*") {
                @Override
                public void doPrint(String str) {
                    System.out.print(str);
                }

                @Override
                public void doPrintln(String str) {
                    System.out.println("");
                }
            };

            String titleRow[] = new String[4];
            titleRow[0] = "Method Name";
            titleRow[1] = "Count";
            titleRow[2] = "CultivTime";
            titleRow[3] = "callTime";

            printer.addTitle(titleRow);
            List<MethodInstr> temps = MyBCIMethod.getMethodInstrList();

//            for (MethodInstr methodList : temps) {
//                String row[] = new String[4];
//                row[0] = methodList.getMethodName();
//                row[1] = String.valueOf(methodList.getCallTime());
//                row[2] = String.valueOf(methodList.getCultivTime());
//                row[3] = String.valueOf(methodList.getCallTime());
//
//                printer.add(row);
//            }

            printer.print();

            try {
                Thread.sleep(1000);
//                System.out.print("Everything on the console will cleared");
//                System.out.print("\033[H\033[2J");
//                System.out.flush();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
