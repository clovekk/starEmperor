package launcher;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class GameLauncher {
    public GameLauncher() {
        //setup flatlaf so the window doesnt look like it came straight out of windows 95
        FlatLightLaf.setup();

        //setup the JFrame/window
        JFrame frame = new JFrame("Graph editor");
        frame.setLayout(null);
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setLocationRelativeTo(null);
    }
}
