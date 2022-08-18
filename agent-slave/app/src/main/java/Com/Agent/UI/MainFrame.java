package Com.Agent.UI;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    final JFrame frame = new JFrame("agent Slave");

    MainFrame(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        frame.setSize(1280  , 800);
        frame.setVisible(true);
        frame.setContentPane(new MainPanel(new BorderLayout(5,5)));



    }
}
