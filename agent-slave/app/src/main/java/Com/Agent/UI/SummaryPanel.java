package Com.Agent.UI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SummaryPanel extends JPanel {
    public SummaryPanel(LayoutManager layout) {
        super(layout);
        this.setBorder(new TitledBorder("Summary"));
    }
}
