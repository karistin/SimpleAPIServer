package Com.Agent.Print;

import Com.Agent.MyBCIMethod;
import Com.Entity.MethodInstr;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Frame {

    Frame(){
        final JFrame frame = new JFrame("agent Slave");
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800  , 600);
        frame.setVisible(true);

        final JPanel eventPannel = new JPanel(new BorderLayout(5,5));
        eventPannel.setBorder(new TitledBorder("event log"));

        final JPanel gui = new JPanel(new BorderLayout(5,5));
        gui.setBorder(new TitledBorder("BorderLayout(5,5)"));

        JPanel plafComponents = new JPanel(new GridLayout(1,2,5,5));
        plafComponents.setBorder(new TitledBorder("Header"));

        JPanel menu = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 3));
//        menu.setBorder(new TitledBorder("menu"));
        JPanel template = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 3));
//        template.setBorder(new TitledBorder("template"));

        plafComponents.add(menu);
        plafComponents.add(template);

        final UIManager.LookAndFeelInfo[] plafInfos = UIManager.getInstalledLookAndFeels();
        String[] plafNames = new String[plafInfos.length];

        for (int i = 0; i < plafInfos.length; i++) {
            plafNames[i] = plafInfos[i].getName();
        }
        JButton btn1 = new JButton("Method");
        JButton btn2 = new JButton("Event Log");
        JButton btn3 = new JButton("Monitoring");
        JButton btn4 = new JButton("Summary");



        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Monitoring");
            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Summary");
            }
        });

        menu.add(btn1);
        menu.add(btn2);
        menu.add(btn3);
        menu.add(btn4);


        final JComboBox plafChooser = new JComboBox(plafNames);
        template.add(plafChooser);

        final JCheckBox pack = new JCheckBox("Pack on PLAF change");
        template.add(pack);


        plafChooser.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                int index = plafChooser.getSelectedIndex();
                try {
                    UIManager.setLookAndFeel(
                            plafInfos[index].getClassName() );
                    SwingUtilities.updateComponentTreeUI(frame);
                    if (pack.isSelected()) {
                        frame.pack();
                        frame.setMinimumSize(frame.getSize());
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        } );

        JPanel dynamicLabels = new JPanel(new BorderLayout(4, 4));
        dynamicLabels.setBorder(new TitledBorder("BorderLayout(4,4"));

        final JPanel labels = new JPanel(new GridLayout(0, 2, 3, 3));
        labels.setBorder(new TitledBorder("GridLayout(0,2,2,3)"));

        JButton addNew = new JButton("Add Another Label");
        dynamicLabels.add(addNew, BorderLayout.NORTH);
        addNew.addActionListener(new ActionListener() {
            private  int labelCount = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                labels.add(new JLabel("Label " + ++labelCount));
                frame.validate();
            }
        });
        dynamicLabels.add(new JScrollPane(labels), BorderLayout.CENTER);



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
//        Get System Data
//        String[] a = new String[0];
//        String[] names = System.getProperties().stringPropertyNames().toArray(a);
//        String[][] data = new String[names.length][2];
//        for (int ii=0; ii<names.length; ii++) {
//            data[ii][0] = names[ii];
//            data[ii][1] = System.getProperty(names[ii]);
//        }


        Font boldFont = new Font("Bold", Font.BOLD,50);

        DefaultTableModel model = new DefaultTableModel(data, header);
        JTable table = new JTable(model);
        model.fireTableDataChanged();

//        table.getColumn("Method").setPreferredWidth(200);
//        table.setAutoCreateRowSorter(true);
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


//        DataModelThread dataThread = new DataModelThread();
//        dataThread.setDaemon(true);
//        dataThread.run(model);
//
        

        JPanel imagePanel = new JPanel(new GridBagLayout());
        imagePanel.setBorder(
                new TitledBorder("GridBagLayout()") );
        BufferedImage bi = new BufferedImage(
                200,200,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        GradientPaint gp = new GradientPaint(
                20f,20f,Color.red, 180f,180f,Color.yellow);
        g.setPaint(gp);
        g.fillRect(0,0,200,200);
        ImageIcon ii = new ImageIcon(bi);
        JLabel imageLabel = new JLabel(ii);
        imagePanel.add( imageLabel, null );

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                tableScroll,
                new JScrollPane(imagePanel));

        frame.pack();

        frame.setLocationRelativeTo(null);
        try {
            // 1.6+
            frame.setLocationByPlatform(true);
            frame.setMinimumSize(frame.getSize());
        } catch(Throwable ignoreAndContinue) {
        }

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn1.setBackground(Color.gray);
                btn2.setBackground(Color.LIGHT_GRAY);
                System.out.println("Event Log CLick");
                dynamicLabels.setVisible(false);
                splitPane.setVisible(false);
                eventPannel.setVisible(true);
            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn1.setBackground(Color.LIGHT_GRAY);
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
                dynamicLabels.setVisible(true);
                splitPane.setVisible(true);
                eventPannel.setVisible(false);
            }
        });
        gui.add( splitPane, BorderLayout.CENTER );
        gui.add(dynamicLabels, BorderLayout.WEST);
        gui.add(plafComponents, BorderLayout.NORTH);
        frame.setContentPane(eventPannel);
        frame.setContentPane(gui);
    }
}
