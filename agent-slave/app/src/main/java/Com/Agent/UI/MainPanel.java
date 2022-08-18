package Com.Agent.UI;

import Com.Agent.MyBCIMethod;
import Com.Entity.MethodInstr;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainPanel extends JPanel {
    public MethodPanel methodPanel = new MethodPanel(new GridLayout(20,5,5,5));
    public EventPanel eventPanel = new EventPanel(new GridLayout(20,5,5,5));
    public MonitoringPanel monitoringPanel = new MonitoringPanel(new GridLayout(20,5,5,5));
    public SummaryPanel summaryPanel = new SummaryPanel(new GridLayout(20,5,5,5));

    JButton methodbtn = new JButton("Method");
    JButton eventbtn = new JButton("Event Log");
    JButton monitoringbtn = new JButton("Monitoring");
    JButton summarybtn = new JButton("Summary");
    public MainPanel(LayoutManager layout) {
        super(layout);
        JPanel menu =  new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        menu.setBorder(new TitledBorder("Header"));


        menu.add(methodbtn);
        menu.add(eventbtn);
        menu.add(monitoringbtn);
        menu.add(summarybtn);
        CardLayout card = new CardLayout(5, 5);
        JPanel content = new JPanel(card);
        content.setBorder(new TitledBorder("content"));
        content.add("method",methodPanel);
        content.add("event",eventPanel);
        content.add("monitor",monitoringPanel);
        content.add("summary",summaryPanel);
        card.show(content, "monitor");

        methodbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(content, "method");

            }
        });

        eventbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(content, "event");
            }
        });

        monitoringbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(content, "monitor");
            }
        });

        summarybtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(content, "summary");
            }
        });

        this.add(menu,BorderLayout.NORTH);
        this.add(content, BorderLayout.CENTER);
    }

}
