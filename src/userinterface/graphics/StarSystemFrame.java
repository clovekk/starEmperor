package userinterface.graphics;

import javax.swing.*;
import java.awt.*;

public class StarSystemFrame extends JFrame {
    private volatile StarSystemPanel starSystemPanel;

    public StarSystemFrame(String title, StarSystemPanel starSystemPanel) throws HeadlessException {
        super(title);
        this.starSystemPanel = starSystemPanel;

        this.setName(starSystemPanel.getStarSystem().getName());
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setPreferredSize(starSystemPanel.getSize());
        this.pack();

        this.add(starSystemPanel);

    }

    public void update() {
        starSystemPanel.update();
    }

}
