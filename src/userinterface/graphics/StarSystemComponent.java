package userinterface.graphics;

import game.StarSystem;
import game.WorldManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StarSystemComponent extends JComponent {
    private volatile StarSystem starSystem;
    private static volatile StarSystem selectedSystem = null;
    private volatile FleetComponent fleetComponent;
    private volatile WorldManager worldManager;

    public StarSystemComponent(StarSystem starSystem, WorldManager worldManager, int x, int y) {
        this.starSystem = starSystem;
        this.worldManager = worldManager;

        this.setLayout(null);
        this.setSize(100, 100);
        this.setLocation(x - getWidth(), y - getHeight());

        this.fleetComponent = new FleetComponent(null, getWidth() / 2 + 10, getHeight() / 2 + 10);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //check if its left mouse button
                if (SwingUtilities.isLeftMouseButton(e)) {
                    System.out.println("clicked: " + getStarSystem());
                    //current selected system is already this system
                    if (selectedSystem == getStarSystem()) {
                        selectedSystem = null;
                    //current selected system is different that this one
                    } else {
                        selectedSystem = getStarSystem();
                    }
                    //getParent().repaint();

                    //TODO make a window with star system info
                }
            }
        });

        this.add(fleetComponent);
        this.fleetComponent.setVisible(false);
    }

    public StarSystem getStarSystem() {
        return starSystem;
    }
    public static StarSystem getSelectedSystem() {
        return selectedSystem;
    }

    public void setStarSystem(StarSystem starSystem) {
        this.starSystem = starSystem;
    }

    public void update() {
        if (!starSystem.getFleets().isEmpty()) {
            if (this.fleetComponent.getFleet() != starSystem.getFleets().get(0)) {
                this.fleetComponent.setFleet(starSystem.getFleets().get(0));
            }
            this.fleetComponent.setVisible(true);
        } else {
            if (this.fleetComponent.getFleet() != null) {
                this.fleetComponent.setFleet(null);
            }
            this.fleetComponent.setVisible(false);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();

        /*graphics.setColor(Color.MAGENTA);
        graphics.fillRect(0, 0, 4, 4);

        graphics.setColor(Color.MAGENTA);
        graphics.fillRect(getWidth() - 4, getHeight() - 4, 4, 4);*/

        int sysSize = 15;

        if (starSystem.getOwnerID() != null) {
            graphics.setColor(worldManager.getWorld().getPlayers().get(starSystem.getOwnerID()).getColor().toColor());
            graphics.fillOval(getWidth() / 2 - 5 * sysSize / 2, getHeight() / 2 - 5 * sysSize / 2, sysSize * 5, sysSize * 5);
        }

        graphics.setStroke(new BasicStroke(1));
        graphics.setColor(Color.WHITE);
        graphics.fillOval(getWidth() / 2 - sysSize / 2, getHeight() / 2 - sysSize / 2, sysSize, sysSize);

        if (selectedSystem == this.starSystem) {
            graphics.setStroke(new BasicStroke(3));
            graphics.setColor(new Color(236, 121, 2));
            sysSize = 35;
            graphics.drawOval(getWidth() / 2 - sysSize / 2, getHeight() / 2 - sysSize / 2, sysSize, sysSize);
        }
    }
}
