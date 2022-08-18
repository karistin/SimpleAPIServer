package Com.Agent.UI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MonitoringPanel extends JPanel {
    public MonitoringPanel(LayoutManager layout) {
        super(layout);
        this.setBorder(new TitledBorder("monitoring"));
    }
}
