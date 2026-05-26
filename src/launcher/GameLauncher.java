package launcher;

import com.formdev.flatlaf.FlatLightLaf;
import game.Game;
import game.World;
import game.WorldLoader;
import game.WorldManager;
import userinterface.graphics.GraphicsUserInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Objects;

public class GameLauncher {
    public GameLauncher() {
    }

    public void launch() {
        //setup flatlaf so the window doesn't look like it came straight out of Windows 95
        FlatLightLaf.setup();

        //setup the JFrame/window
        JFrame frame = new JFrame("Star Emperor");
        frame.getContentPane().setPreferredSize(new Dimension(1280, 720)); //set the inside of the JFrame to the desired size (excluding the window decorations)
        frame.pack();

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBackground(Color.BLUE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        Image menuTheme;
        try {
            menuTheme = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menu_theme.png"))).getScaledInstance(frame.getContentPane().getWidth(), frame.getContentPane().getHeight(), Image.SCALE_DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageIcon menuIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/menu_icon.png")));
        menuIcon = new ImageIcon(menuIcon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
        frame.setIconImage(menuIcon.getImage());

        //menu setup
        MenuPanel menuPanel = getMenu(menuTheme, frame);
        menuPanel.setLocation(0, 0);

        frame.add(menuPanel);

        frame.setVisible(true);
    }

    private static MenuPanel getMenu(Image menuTheme, JFrame frame) {
        MenuPanel menuPanel = new MenuPanel(menuTheme);

        //New Game button
        JButton newGameButton = new JButton("New Game");
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

        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorldLoader worldLoader = new WorldLoader();
                World world = worldLoader.loadWorld();

                WorldManager worldManager = new WorldManager(world, false, 10);
                Game game = new Game(worldManager, new GraphicsUserInterface(worldManager, 60), false);
                game.startGame();
                game.startDisplay();

                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        //add buttons to menu
        menuPanel.add(newGameButton);
        menuPanel.add(loadGameButton);
        return menuPanel;
    }
}
