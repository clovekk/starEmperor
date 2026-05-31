package userinterface.graphics;

import game.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * the panel that opens when resource amount is clicked, contains info about production/consumption
 */
public class ResourcePanel extends JPanel {
    private volatile WorldManager worldManager;
    private volatile Player player;
    private volatile Resource resource;
    private volatile DefaultListModel<String> listModel;

    public ResourcePanel(WorldManager worldManager, Player player, Resource resource) {
        this.worldManager = worldManager;
        this.player = player;
        this.resource = resource;
        this.listModel = new DefaultListModel<>();

        //set up panel
        this.setLayout(null);
        this.setSize(200, 400);
        this.setPreferredSize(new Dimension(200, 400));
        this.setBackground(Color.BLUE);

        //set values
        updateSystemResources();

        //setup jlist
        JList<String> starSystemList = new JList<>(listModel);
        starSystemList.setLocation(0, 0);
        starSystemList.setSize(200, 400);
        starSystemList.setPreferredSize(new Dimension(200, 400));
        starSystemList.setBackground(Color.BLUE);
        starSystemList.setForeground(Color.WHITE);
        starSystemList.setLayoutOrientation(JList.VERTICAL);
        starSystemList.setVisibleRowCount(-1);

        Font resourceInfoFont = new Font(starSystemList.getFont().getFontName(), Font.BOLD, starSystemList.getFont().getSize() + 2);
        starSystemList.setFont(resourceInfoFont);

        //setup jscrollpane
        JScrollPane starSystemScrollPane = new JScrollPane(starSystemList);
        starSystemScrollPane.setLocation(0, 0);
        starSystemScrollPane.setSize(200, 400);
        starSystemScrollPane.setPreferredSize(new Dimension(200, 400));
        starSystemScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        starSystemScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(starSystemScrollPane);
    }

    public Resource getResource() {
        return resource;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    /**
     * updates the star system production values in the resource info panel
     */
    public void updateSystemResources() {
        if (!listModel.contains("Star Systems:")) {
            listModel.add(0, "Star Systems:");
        }
        int systemResourceAmount = 0;
        ArrayList<StarSystem> playerOwnedStarSystems = worldManager.getWorld().getSystemsOwnedBy(player);
        for (StarSystem starSystem : playerOwnedStarSystems) {
            if (starSystem.getResourceAmount(resource) != 0) {
                systemResourceAmount += 1;
                String systemResources = " " + starSystem.getName() + ": " + resource.getName() + ": " + starSystem.getResourceAmount(resource);
                if (!listModel.contains(systemResources)) {
                    listModel.add(systemResourceAmount, systemResources);
                }
            }
        }
        updateFleetResources(systemResourceAmount);
    }

    /**
     * updates the fleet consumption values in the resource info panel
     * @param startIndex
     */
    public void updateFleetResources(int startIndex) {
        int currentIndex = startIndex + 1;
        if (!listModel.contains("Fleet Upkeep:")) {
            listModel.add(currentIndex, " ");
            currentIndex += 1;
            listModel.add(currentIndex, "Fleet Upkeep:");
            currentIndex += 1;
        }
        ArrayList<Fleet> playerOwnedFleets = worldManager.getWorld().getFleetsOwnedBy(player);
        for (Fleet fleet : playerOwnedFleets) {
            String fleetUpkeep = " " + fleet.getName() + ": " + resource.getName() + ": " + fleet.getUpkeepAmount(resource);
            if (!listModel.contains(fleetUpkeep)) {
                listModel.add(currentIndex, fleetUpkeep);
                currentIndex += 1;
            }
        }
    }
}
