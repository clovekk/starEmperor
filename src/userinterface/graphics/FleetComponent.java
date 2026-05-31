package userinterface.graphics;

import game.Fleet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * represents the fleet instance
 */
public class FleetComponent extends JComponent {
    private volatile Fleet fleet;
    private static volatile Fleet selectedFleet = null;

    public FleetComponent(Fleet fleet, int x, int y) {
        this.fleet = fleet;

        //set up component
        this.setLayout(null);
        this.setSize(30, 40);
        this.setLocation(x, y);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //check for left mouse button
                if (SwingUtilities.isLeftMouseButton(e)) {
                    //(de)select fleet
                    System.out.println("clicked: " + getFleet());
                    if (selectedFleet == getFleet()) {
                        selectedFleet = null;
                    } else {
                        selectedFleet = getFleet();
                    }
                }
            }
        });
    }

    public static Fleet getSelectedFleet() {
        return selectedFleet;
    }
    public Fleet getFleet() {
        return fleet;
    }

    public void setFleet(Fleet fleet) {
        this.fleet = fleet;
        if (fleet != null) {
            //set the tooltip as fleet name
            this.setToolTipText(fleet.getName());
        } else {
            this.setToolTipText(null);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();

        Polygon fleetSign = new Polygon(new int[]{0, 15, 30}, new int[]{35, 0, 35}, 3);
        Polygon fleetSelectedSign = new Polygon(new int[]{-5, 15, 35}, new int[]{38, -5, 38}, 3);

        graphics.setColor(new Color(33, 124, 12));
        if (selectedFleet == fleet) {
            graphics.setColor(new Color(192, 198, 8));
            graphics.fillPolygon(fleetSelectedSign);
            graphics.setColor(new Color(76, 255, 35));
        }
        graphics.fillPolygon(fleetSign);
    }
}
