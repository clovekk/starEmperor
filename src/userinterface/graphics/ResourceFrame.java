package userinterface.graphics;

import javax.swing.*;
import java.awt.*;

public class ResourceFrame extends JFrame {
    private volatile ResourcePanel resourcePanel;

    public ResourceFrame(String title, ResourcePanel resourcePanel) throws HeadlessException {
        super(title);
        this.resourcePanel = resourcePanel;

        this.setName(resourcePanel.getResource().getName());
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(200, 400);
        this.getContentPane().setPreferredSize(resourcePanel.getSize());
        this.pack();

        this.add(resourcePanel);
    }

    public void update() {
        resourcePanel.updateSystemResources();
    }
}
