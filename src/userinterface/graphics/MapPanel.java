package userinterface.graphics;

import game.StarSystem;
import game.WorldManager;
import graph.Edge;
import graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Collection;

public class MapPanel extends JPanel {
    private volatile WorldManager worldManager;
    private volatile int zoomCoefficient;

    private int relativeMouseX;
    private int relativeMouseY;

    public MapPanel(WorldManager worldManager, int zoomCoefficient) {
        this.worldManager = worldManager;
        this.zoomCoefficient = zoomCoefficient;

        this.setLayout(null);
        this.setSize(200 * zoomCoefficient, 200 * zoomCoefficient);
        this.setBackground(new Color(5, 2, 38));

        //map movement mouse( motion) listeners
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (SwingUtilities.isMiddleMouseButton(e)) {
                    //mouse position relative to the origin of the panel is saved
                    relativeMouseX = e.getX();
                    relativeMouseY = e.getY();
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (SwingUtilities.isMiddleMouseButton(e)) {
                    //difference between mouse positions is calculated
                    int deltaX = e.getX() - relativeMouseX;
                    int deltaY = e.getY() - relativeMouseY;

                    //4 checks to see if the panel is being dragged out of view (because of the danger of not being able to move the panel back)
                    //left border too right
                    if (getX() + deltaX > getParent().getWidth() - 100 * zoomCoefficient) {
                        deltaX = getParent().getWidth() - 100 * zoomCoefficient - getX();
                    }
                    //top border too low
                    if (getY() + deltaY > getParent().getHeight() - 100 * zoomCoefficient) {
                        deltaY = getParent().getHeight() - 100 * zoomCoefficient - getY();
                    }
                    //right border too left
                    if (getX() + deltaX + getWidth() < 100 * zoomCoefficient) {
                        deltaX = 100 * zoomCoefficient + getX();
                    }
                    //bottom border too high
                    if (getY() + deltaY + getHeight() < 100 * zoomCoefficient) {
                        deltaY = 100 * zoomCoefficient + getY();
                    }

                    //location of the panel is updated by the mouse position difference
                    setLocation(getX() + deltaX, getY() + deltaY);
                }
            }
        });
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }
    public void setWorldManager(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics graphics = g.create();

        Graph<StarSystem> starSystems = worldManager.getWorld().getStarSystems();

        graphics.setColor(Color.WHITE);
        for (StarSystem starSystem : starSystems.getAll()) {
            //graphics.fillOval((50 + starSystem.getX()) * zoomCoefficient - 15 / 2, (50 + starSystem.getY()) * zoomCoefficient - 15 / 2, 15, 15);
            add(new StarSystemComponent(starSystem, (50 + starSystem.getX()) * zoomCoefficient - 15 / 2, (50 + starSystem.getY()) * zoomCoefficient - 15 / 2));
        }

        for (Edge<StarSystem> edge : starSystems.getEdges().values()) {
            graphics.drawLine((50 + starSystems.get(edge.getTail()).getX()) * zoomCoefficient, (50 + starSystems.get(edge.getTail()).getY()) * zoomCoefficient, (50 + starSystems.get(edge.getHead()).getX()) * zoomCoefficient, (50 + starSystems.get(edge.getHead()).getY()) * zoomCoefficient);
        }
    }
}
