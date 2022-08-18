package Com.Agent.UI;

import Com.Agent.MyBCIMethod;
import Com.Entity.MethodInstr;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MethodPanel extends JPanel{
    DefaultTableModel model;
    public MethodPanel(LayoutManager layout) {
        super(layout);
        this.setBorder(new TitledBorder("Method"));

        String[] header = {"Method", "Call","Average(ms)","Total(ms)"};
        Map<String, MethodInstr> methodInstrMap = MyBCIMethod.getMethodInstrList();
        List<String> keyList = new ArrayList<>(methodInstrMap.keySet());
        Object[][] data = new Object[keyList.size()][header.length];

        for (int i =0; i<keyList.size(); i++)
        {
            MethodInstr methodInstr = methodInstrMap.get(keyList.get(i));
            data[i][0] = methodInstr.getMethodName();
            data[i][1] = methodInstr.getCalls();
            data[i][2] = methodInstr.getSecond();
            data[i][3] = methodInstr.getTotalTime();
        }

        model = new DefaultTableModel(data, header);
        JTable table = new JTable(model);
        model.fireTableDataChanged();

        table.putClientProperty("JTable.autoStartsEdit", Boolean.FALSE);



        try {
            // 1.6+
            table.setAutoCreateRowSorter(true);
        } catch(Exception continuewithNoSort) {
        }
        JScrollPane tableScroll = new JScrollPane(table);
        Dimension tablePreferred = tableScroll.getPreferredSize();
        tableScroll.setPreferredSize(
                new Dimension(tablePreferred.width, tablePreferred.height/2) );

        this.add(table, BorderLayout.CENTER);


    }
}
