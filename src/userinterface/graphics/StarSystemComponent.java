package userinterface.graphics;

import game.Player;
import game.StarSystem;
import game.WorldManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

/**
 * Represents the star system as a drawable object
 */
public class StarSystemComponent extends JComponent {
    private volatile StarSystem starSystem;
    private static volatile StarSystem selectedSystem = null;
    private volatile FleetComponent fleetComponent;
    private volatile WorldManager worldManager;
    private volatile Player player;
    private static volatile StarSystemFrame starSystemFrame;

    public StarSystemComponent(StarSystem starSystem, WorldManager worldManager, Player player, int x, int y) {
        this.starSystem = starSystem;
        this.worldManager = worldManager;
        this.player = player;

        //set up the panel
        this.setLayout(null);
        this.setSize(100, 100);
        this.setLocation(x - getWidth(), y - getHeight());
        this.setToolTipText(starSystem.getName());

        //the fleet component representing fleets in the system
        this.fleetComponent = new FleetComponent(null, getWidth() / 2 + 10, getHeight() / 2 + 10);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //check if its left mouse button
                if (SwingUtilities.isLeftMouseButton(e)) {
                    //System.out.println("clicked: " + getStarSystem()); //temp
                    //current selected system is already this system
                    if (selectedSystem == getStarSystem()) {
                        selectedSystem = null;

                        starSystemFrame.dispatchEvent(new WindowEvent(starSystemFrame, WindowEvent.WINDOW_CLOSING));
                        starSystemFrame = null;
                    //current selected system is different that this one
                    } else {
                        if (selectedSystem != null) {
                            starSystemFrame.dispatchEvent(new WindowEvent(starSystemFrame, WindowEvent.WINDOW_CLOSING));
                            starSystemFrame = null;
                        }

                        selectedSystem = getStarSystem();

                        starSystemFrame = new StarSystemFrame(selectedSystem.getName(), new StarSystemPanel(worldManager, selectedSystem, player));
                        starSystemFrame.setVisible(true);
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

    /**
     * updates the values of this component and its components
     */
    public void update() {
        //fleet present?
        if (!starSystem.getFleets().isEmpty()) {
            //yes, draw fleets
            if (this.fleetComponent.getFleet() != starSystem.getFleets().getFirst()) {
                this.fleetComponent.setFleet(starSystem.getFleets().getFirst());
            }
            this.fleetComponent.setVisible(true);
        } else {
            //no dont draw fleets
            if (this.fleetComponent.getFleet() != null) {
                this.fleetComponent.setFleet(null);
            }
            this.fleetComponent.setVisible(false);
        }
        if (starSystemFrame != null) {
            starSystemFrame.update();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();

        //temp for debug
        /*graphics.setColor(Color.MAGENTA);
        graphics.fillRect(0, 0, 4, 4);

        graphics.setColor(Color.MAGENTA);
        graphics.fillRect(getWidth() - 4, getHeight() - 4, 4, 4);*/

        int sysSize = 15;

        //system is owned by player
        if (starSystem.getOwnerID() != null) {
            graphics.setColor(worldManager.getWorld().getPlayers().get(starSystem.getOwnerID()).getColor().toColor());
            graphics.fillOval(getWidth() / 2 - 5 * sysSize / 2, getHeight() / 2 - 5 * sysSize / 2, sysSize * 5, sysSize * 5);
        }

        //main system body
        graphics.setStroke(new BasicStroke(1));
        graphics.setColor(Color.WHITE);
        graphics.fillOval(getWidth() / 2 - sysSize / 2, getHeight() / 2 - sysSize / 2, sysSize, sysSize);

        //this system is selected
        if (selectedSystem == this.starSystem) {
            graphics.setStroke(new BasicStroke(3));
            graphics.setColor(new Color(236, 121, 2));
            sysSize = 35;
            graphics.drawOval(getWidth() / 2 - sysSize / 2, getHeight() / 2 - sysSize / 2, sysSize, sysSize);
        }
    }
}
