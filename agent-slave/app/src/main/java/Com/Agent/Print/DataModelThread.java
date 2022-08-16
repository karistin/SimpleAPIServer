package Com.Agent.Print;

import Com.Agent.MyBCIMethod;
import Com.Entity.MethodInstr;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataModelThread extends Thread{

    public void run(DefaultTableModel model) {
        try {
            String[] header = {"Method", "Call","Average(ms)","Total(ms)"};
            Map<String, MethodInstr> methodInstrMap = MyBCIMethod.getMethodInstrList();
            List<String> keyList = new ArrayList<>(methodInstrMap.keySet());
            Object[][] data = new Object[keyList.size()][header.length];

            for (int i =0; i<keyList.size(); i++)
            {
                MethodInstr methodInstr = methodInstrMap.get(keyList.get(i));

                data[i][0] = methodInstr.getPackageName()+methodInstr.getClassName()+"/"+methodInstr.getMethodName();
                data[i][1] = methodInstr.getCalls();
                data[i][2] = methodInstr.getSecond();
                data[i][3] = methodInstr.getTotalTime();
                model.setDataVector(data, header);
            }
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
