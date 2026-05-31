package userinterface.graphics;

import game.Player;
import game.Resource;
import game.StarSystem;
import game.WorldManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class StarSystemPanel extends JPanel {
    private volatile StarSystem starSystem;
    private volatile WorldManager worldManager;
    private volatile Player player;
    private volatile JButton travelButton;
    private volatile JButton claimSystemButton;
    private volatile JLabel resourcesLabel;

    public StarSystemPanel(WorldManager worldManager, StarSystem starSystem, Player player) {
        this.worldManager = worldManager;
        this.starSystem = starSystem;
        this.player = player;
        this.travelButton = new JButton("Travel to system");
        this.claimSystemButton = new JButton("Claim system");
        this.resourcesLabel = new JLabel();

        this.setLayout(null);
        this.setSize(300, 250);
        this.setPreferredSize(new Dimension(300, 250));
        this.setBackground(Color.BLUE);

        travelButton.setBackground(Color.GRAY);
        travelButton.setForeground(Color.WHITE);
        travelButton.setSize(200,50);
        travelButton.setLocation(0, 0);
        travelButton.setEnabled(false);
        travelButton.setToolTipText("You need to select a fleet to send to this system.");

        claimSystemButton.setBackground(Color.GRAY);
        claimSystemButton.setForeground(Color.WHITE);
        claimSystemButton.setSize(200, 50);
        claimSystemButton.setLocation(0, 50);
        claimSystemButton.setEnabled(false);
        claimSystemButton.setToolTipText("You need a fleet in this system and 10 metals and 5 energy in your stockpile in-oder to take control over this system.");

        resourcesLabel.setForeground(Color.WHITE);
        resourcesLabel.setSize(200, 50);
        resourcesLabel.setLocation(0, 100);
        resourcesLabel.setText(Resource.toPrettyHTMLString(starSystem.getResources()));

        travelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worldManager.getWorld().moveFleetToSystem(FleetComponent.getSelectedFleet(), starSystem);
            }
        });

        claimSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getResourceAmount("metals") >= 10 && player.getResourceAmount("energy") >= 5) {
                    ArrayList<Resource> cost = new ArrayList<>(Arrays.asList(new Resource("metals", 10), new Resource("energy", 5)));

                    starSystem.setOwnerID(player.getId());
                    player.subtractResources(cost);
                }
            }
        });

        this.add(travelButton);
        this.add(claimSystemButton);
        this.add(resourcesLabel);
    }

    public void update() {
        if (FleetComponent.getSelectedFleet() != null) {
            travelButton.setEnabled(true);
        } else {
            travelButton.setEnabled(false);
        }
        if (!starSystem.getFleets().isEmpty() && !player.getId().equals(starSystem.getOwnerID()) && player.getResourceAmount("metals") >= 10 && player.getResourceAmount("energy") >= 5) {
            claimSystemButton.setEnabled(true);
        } else {
            claimSystemButton.setEnabled(false);
        }
        resourcesLabel.setText(Resource.toPrettyHTMLString(starSystem.getResources()));
    }

    public StarSystem getStarSystem() {
        return starSystem;
    }
    public void setStarSystem(StarSystem starSystem) {
        this.starSystem = starSystem;
    }
}
