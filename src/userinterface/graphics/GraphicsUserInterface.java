package userinterface.graphics;

import com.formdev.flatlaf.FlatLightLaf;
import game.Player;
import game.WorldManager;
import userinterface.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class GraphicsUserInterface extends Thread implements UserInterface {
    private volatile WorldManager worldManager;
    private volatile Player player;
    private volatile AtomicBoolean end;
    private int targetFps;
    private long targetFrameTime;

    public GraphicsUserInterface(WorldManager worldManager, Player player, int targetFps) {
        this.worldManager = worldManager;
        this.player = player;
        this.end = new AtomicBoolean(false);
        this.targetFps = targetFps;
        this.targetFrameTime = 1000000000 / targetFps;
    }

    public GraphicsUserInterface() {
        this.worldManager = new WorldManager();
        this.player = null;
        this.end = new AtomicBoolean(false);
        this.targetFps = 60;
        this.targetFrameTime = 1000000000 / targetFps;
    }

    @Override
    public void startDisplaying() {
        this.start();
    }

    @Override
    public void run() {
        super.run();

        setName("StarEmperor_GUI");

        //setup flatlaf so the window doesn't look like it came straight out of Windows 95
        FlatLightLaf.setup();

        //setup the JFrame/window
        JFrame frame = new JFrame("Star Emperor");
        frame.getContentPane().setPreferredSize(new Dimension(1280, 720)); //set the inside of the JFrame to the desired size (excluding the window decorations)
        frame.pack();

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(5, 2, 38));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        ImageIcon menuIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/game_icon.png")));
        menuIcon = new ImageIcon(menuIcon.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
        frame.setIconImage(menuIcon.getImage());

        MapPanel mapPanel = new MapPanel(worldManager, 3);
        mapPanel.setLocation(0, 0);

        InfoBarPanel infoBarPanel = new InfoBarPanel(worldManager, player);
        infoBarPanel.setLocation(0, 0);

        frame.add(infoBarPanel);
        frame.add(mapPanel);

        frame.setVisible(true);

        while(!end.get()) {
            long frameStartTime = System.nanoTime();

            //start of update loop -------------------------------------------------------------------------------------

            infoBarPanel.repaint();
            mapPanel.update();

            //end of update loop ---------------------------------------------------------------------------------------

            long frameTime = System.nanoTime() - frameStartTime;
            if (targetFrameTime - frameTime > 0) {
                try {
                    Thread.sleep((targetFrameTime - frameTime) / 1000000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
