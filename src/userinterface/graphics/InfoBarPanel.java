package userinterface.graphics;

import game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * represents the information panel at the top of the screen
 */
public class InfoBarPanel extends JPanel {
    private volatile WorldManager worldManager;
    private volatile Player player;
    private volatile JLabel dateLabel;
    private volatile JButton pauseButton;
    private volatile ArrayList<JButton> resourceButtons;
    private volatile ResourceFrame resourceFrame;
    private volatile JButton buyFleetButton;
    private volatile JButton saveGameButton;
    private volatile JButton exitGameButton;
    private volatile AtomicBoolean guiEnd;

    public InfoBarPanel(AtomicBoolean guiEnd, WorldManager worldManager, Player player) {
        this.guiEnd = guiEnd;
        this.worldManager = worldManager;
        this.player = player;
        this.dateLabel = new JLabel();
        this.pauseButton = new JButton();
        this.resourceButtons = new ArrayList<>();
        this.buyFleetButton = new JButton();
        this.saveGameButton = new JButton();
        this.exitGameButton = new JButton();

        //set up panel
        this.setLayout(null);
        this.setSize(1280, 40);
        this.setBackground(new Color(0, 107, 133));

        //exit button
        exitGameButton.setText("Exit Game");
        exitGameButton.setBackground(Color.GRAY);
        exitGameButton.setForeground(Color.WHITE);
        exitGameButton.setSize(100, 30);
        exitGameButton.setLocation(1280 - exitGameButton.getWidth() - 10, (this.getHeight() - exitGameButton.getHeight()) / 2);

        //save button
        saveGameButton.setText("Save Game");
        saveGameButton.setBackground(Color.GRAY);
        saveGameButton.setForeground(Color.WHITE);
        saveGameButton.setSize(100, 30);
        saveGameButton.setLocation(exitGameButton.getX() - saveGameButton.getWidth() - 10, (this.getHeight() - saveGameButton.getHeight()) / 2);

        //pause button
        pauseButton.setBackground(Color.GRAY);
        pauseButton.setForeground(Color.WHITE);
        pauseButton.setSize(80, 30);
        pauseButton.setLocation(saveGameButton.getX() - pauseButton.getWidth() - 10, (this.getHeight() - pauseButton.getHeight()) / 2);

        //current date label
        Font dateLabelFont = new Font(dateLabel.getFont().getFontName(), Font.BOLD, dateLabel.getFont().getSize());
        dateLabel.setFont(dateLabelFont);
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setSize(80, 30);
        dateLabel.setLocation(pauseButton.getX() - dateLabel.getWidth(), (this.getHeight() - dateLabel.getHeight()) / 2);

        //buy fleet button
        buyFleetButton.setText("Buy new fleet");
        buyFleetButton.setToolTipText("You need 15 metals and 10 energy to buy a new fleet.");
        buyFleetButton.setBackground(Color.GRAY);
        buyFleetButton.setForeground(Color.WHITE);
        buyFleetButton.setSize(120, 30);
        buyFleetButton.setLocation(dateLabel.getX() - buyFleetButton.getWidth() - 10, (this.getHeight() - buyFleetButton.getHeight()) / 2);
        buyFleetButton.setEnabled(false);

        //setup resource button for all player resources
        for (int i = 0; i < player.getResources().size(); i++) {
            Resource resource = player.getResources().get(i);

            JButton resourceButton = new JButton(resource.getName() + ": " + resource.getAmount());
            resourceButton.setName(resource.getName());
            resourceButton.setBackground(Color.GRAY);
            resourceButton.setForeground(Color.WHITE);
            resourceButton.setSize(100, 30);
            resourceButton.setLocation(100 + 100 * i, (this.getHeight() - resourceButton.getHeight()) / 2);

            resourceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (resourceFrame != null) {
                        resourceFrame.dispatchEvent(new WindowEvent(resourceFrame, WindowEvent.WINDOW_CLOSING));
                        resourceFrame = null;
                    }

                    resourceFrame = new ResourceFrame("Monthly " + resourceButton.getName(), new ResourcePanel(worldManager, player, resource));
                    resourceFrame.setVisible(true);
                }
            });

            resourceButtons.add(resourceButton);
        }

        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (worldManager.isPaused()) {
                    worldManager.unpauseGame();
                }

                worldManager.setEnd(true);
                guiEnd.set(true);
            }
        });

        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean gameWasPaused = worldManager.isPaused();

                if (!gameWasPaused) {
                    worldManager.pauseGame();
                }

                WorldLoader wl = new WorldLoader();
                wl.saveWorld(worldManager.getWorld());

                if (!gameWasPaused) {
                    worldManager.unpauseGame();
                }
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (worldManager.isPaused()) {
                    worldManager.unpauseGame();
                    //pauseButton.setText("Pause");
                } else {
                    worldManager.pauseGame();
                    //pauseButton.setText("Unpause");
                }
            }
        });

        buyFleetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<StarSystem> playerSystems = worldManager.getWorld().getSystemsOwnedBy(player);
                TextInputFrame textInputFrame = new TextInputFrame("Input fleet name");
                final String[] name = {null};

                textInputFrame.getConfirmButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Random rnd = new Random();

                        //check for valid name and enough resources
                        if (textInputFrame.getInputField().getText() != null && !textInputFrame.getInputField().getText().isBlank() && !textInputFrame.getInputField().getText().isEmpty() && player.getResourceAmount("metals") >= 15 && player.getResourceAmount("energy") >= 10) {
                            name[0] = textInputFrame.getInputField().getText();

                            playerSystems.get(rnd.nextInt(playerSystems.size())).addFleet(new Fleet(name[0], player.getId()));

                            ArrayList<Resource> cost = new ArrayList<>(Arrays.asList(new Resource("metals", 15), new Resource("energy", 10)));
                            player.subtractResources(cost);

                            textInputFrame.dispatchEvent(new WindowEvent(textInputFrame, WindowEvent.WINDOW_CLOSING));
                        }
                    }
                });
            }
        });

        this.add(exitGameButton);
        this.add(saveGameButton);
        this.add(dateLabel);
        this.add(pauseButton);
        this.add(buyFleetButton);

        for (JButton resourceButton : resourceButtons) {
            this.add(resourceButton);
        }
    }

    /**
     * updates all components values
     */
    public void update() {
        for (JButton resourceButton : resourceButtons) {
            resourceButton.setText(resourceButton.getName() + ": " + player.getResourceAmount(resourceButton.getName().toLowerCase()));
        }

        if (resourceFrame != null) {
            resourceFrame.update();
        }

        if (player.getResourceAmount("metals") >= 15 && player.getResourceAmount("energy") >= 10) {
            buyFleetButton.setEnabled(true);
        } else {
            buyFleetButton.setEnabled(false);
        }

        if (worldManager.isPaused()) {
            pauseButton.setForeground(new Color(236, 121, 2));
            pauseButton.setText("Unpause");
        } else {
            pauseButton.setForeground(Color.WHITE);
            pauseButton.setText("Pause");
        }

        dateLabel.setText(worldManager.getWorld().getCurrentDate());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics graphics = g.create();
        graphics.setColor(Color.WHITE);

    }
}
