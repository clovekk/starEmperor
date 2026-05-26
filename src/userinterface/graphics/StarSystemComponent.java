package userinterface.graphics;

import game.StarSystem;

import javax.swing.*;
import java.awt.*;

public class StarSystemComponent extends JComponent {
    private volatile StarSystem starSystem;

    public StarSystemComponent(StarSystem starSystem, int x, int y) {
        this.starSystem = starSystem;

        this.setLocation(x, y);
        this.setLayout(null);
        this.setSize(15, 15);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics graphics = g.create();

        graphics.setColor(Color.WHITE);
        graphics.fillOval(0, 0, 15, 15);
    }
}
