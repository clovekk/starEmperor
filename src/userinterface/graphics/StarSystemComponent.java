package userinterface.graphics;

import game.StarSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StarSystemComponent extends JComponent {
    private volatile StarSystem starSystem;
    private static volatile StarSystem selectedSystem = null;

    public StarSystemComponent(StarSystem starSystem, int x, int y) {
        this.starSystem = starSystem;

        this.setLayout(null);
        this.setSize(45, 45);
        this.setLocation(x - getWidth(), y - getHeight());


        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //check if its left mouse button
                if (SwingUtilities.isLeftMouseButton(e)) {
                    //current selected system is already this system
                    if (selectedSystem == starSystem) {
                        selectedSystem = null;
                    //current selected system is different that this one
                    } else {
                        selectedSystem = starSystem;
                    }
                    getParent().repaint();

                    //TODO make a window with star system info
                }
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();

        int sysSize = 15;
        graphics.setStroke(new BasicStroke(1));
        graphics.setColor(Color.WHITE);
        graphics.fillOval(getWidth() / 2 - sysSize / 2, getHeight() / 2 - sysSize / 2, sysSize, sysSize);

        if (selectedSystem == this.starSystem) {
            graphics.setStroke(new BasicStroke(3));
            graphics.setColor(new Color(236, 121, 2));
            sysSize = 35;
            graphics.drawOval(getWidth() / 2 - sysSize / 2, getHeight() / 2 - sysSize / 2, sysSize, sysSize);
        }
        sysSize = 15;
    }
}
