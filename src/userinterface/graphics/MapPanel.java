package userinterface.graphics;

import game.Player;
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
    private volatile Player player;
    private volatile int zoomCoefficient;
    private volatile ArrayList<StarSystemComponent> starSystemComponents;

    private int relativeMouseX;
    private int relativeMouseY;

    public MapPanel(WorldManager worldManager, Player player, int zoomCoefficient) {
        this.worldManager = worldManager;
        this.player = player;
        this.zoomCoefficient = zoomCoefficient;
        this.starSystemComponents = new ArrayList<>();

        createMap();

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

    public void update() {
        for (Component c : this.getComponents()) {
            if (c.getClass().equals(StarSystemComponent.class)) {
                ((StarSystemComponent) c).update();
            }
        }
    }

    public void createMap() {
        for (StarSystem starSystem : worldManager.getWorld().getStarSystems().getAll()) {
            StarSystemComponent starSystemComponent = new StarSystemComponent(starSystem, worldManager, this.player, (50 + starSystem.getX()) * zoomCoefficient + 100 / 2, (50 + starSystem.getY()) * zoomCoefficient + 100 / 2);
            this.starSystemComponents.add(starSystemComponent);
            add(starSystemComponent);
        }
    }

    public void paintConnections(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();

        graphics.setColor(Color.WHITE);

        Graph<StarSystem> starSystems = worldManager.getWorld().getStarSystems();
        for (Edge<StarSystem> edge : starSystems.getEdges().values()) {
            StarSystem tail = starSystems.get(edge.getTail());
            StarSystem head = starSystems.get(edge.getHead());
            //graphics.drawLine((50 + starSystems.get(edge.getTail()).getX()) * zoomCoefficient, (50 + starSystems.get(edge.getTail()).getY()) * zoomCoefficient, (50 + starSystems.get(edge.getHead()).getX()) * zoomCoefficient, (50 + starSystems.get(edge.getHead()).getY()) * zoomCoefficient);
            graphics.drawLine((50 + starSystems.get(edge.getTail()).getX()) * zoomCoefficient, (50 + starSystems.get(edge.getTail()).getY()) * zoomCoefficient, (50 + starSystems.get(edge.getHead()).getX()) * zoomCoefficient, (50 + starSystems.get(edge.getHead()).getY()) * zoomCoefficient);
        }
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        paintConnections(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics graphics = g.create();

        update();
    }


}
