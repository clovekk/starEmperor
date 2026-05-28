package userinterface.graphics;

import game.Player;
import game.WorldManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoBarPanel extends JPanel {
    private volatile WorldManager worldManager;
    private volatile Player player;
    private volatile JLabel dateLabel;
    private volatile JButton pauseButton;
    private volatile JLabel mineralsLabel;

    public InfoBarPanel(WorldManager worldManager, Player player) {
        this.worldManager = worldManager;
        this.player = player;
        this.dateLabel = new JLabel();
        this.pauseButton = new JButton();
        this.mineralsLabel = new JLabel();

        this.setLayout(null);
        this.setSize(1280, 40);
        this.setBackground(new Color(0, 107, 133));

        pauseButton.setBackground(Color.GRAY);
        pauseButton.setForeground(Color.WHITE);
        pauseButton.setSize(80, 30);
        pauseButton.setLocation(1280 - pauseButton.getWidth() - 10, (this.getHeight() - pauseButton.getHeight()) / 2);

        Font dateLabelFont = new Font(dateLabel.getFont().getFontName(), Font.BOLD, dateLabel.getFont().getSize());
        dateLabel.setFont(dateLabelFont);
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setSize(80, 30);
        dateLabel.setLocation(1280 - dateLabel.getWidth() - pauseButton.getWidth() - 20, (this.getHeight() - dateLabel.getHeight()) / 2);

        mineralsLabel.setFont(dateLabelFont);
        mineralsLabel.setForeground(Color.WHITE);
        mineralsLabel.setSize(200, 30);
        mineralsLabel.setLocation(50, (this.getHeight() - mineralsLabel.getHeight()) / 2);

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

        this.add(mineralsLabel);
        this.add(dateLabel);
        this.add(pauseButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics graphics = g.create();
        graphics.setColor(Color.WHITE);

        if (worldManager.isPaused()) {
            pauseButton.setForeground(new Color(236, 121, 2));
            pauseButton.setText("Unpause");
        } else {
            pauseButton.setForeground(Color.WHITE);
            pauseButton.setText("Pause");
        }

        dateLabel.setText(worldManager.getWorld().getCurrentDate());
        mineralsLabel.setText(player.getResourceList());
    }
}
