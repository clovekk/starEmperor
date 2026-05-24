package launcher;

import com.formdev.flatlaf.FlatLightLaf;
import game.Game;
import game.World;
import game.WorldLoader;
import game.WorldManager;
import userinterface.console.ConsoleUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Menu extends JPanel {
    private Image backgroundImage;

    public Menu(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        this.setLayout(null);
        this.setSize(1280, 720);
        this.setBackground(Color.BLUE);

        //New Game button
        /*JButton newGameButton = new JButton("New Game");
        newGameButton.setBackground(Color.GRAY);
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setSize(300,75);
        newGameButton.setLocation(100, 100);

        //Load Game button
        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setBackground(Color.GRAY);
        loadGameButton.setForeground(Color.WHITE);
        loadGameButton.setSize(300,75);
        loadGameButton.setLocation(100, 225);

        this.add(newGameButton);
        this.add(loadGameButton);

        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorldLoader worldLoader = new WorldLoader();
                World world = worldLoader.loadWorld();

                WorldManager worldManager = new WorldManager(world, false, 10);
                Game game = new Game(worldManager, new ConsoleUserInterface(), false);
                game.startGame();
                game.startDisplay();


            }
        });*/
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}
