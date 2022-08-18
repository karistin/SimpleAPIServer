package Com.Agent.UI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class EventPanel  extends JPanel {
    public EventPanel(LayoutManager layout) {
        super(layout);
        this.setBorder(new TitledBorder("Event"));
    }
}
