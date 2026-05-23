package launcher;

import com.formdev.flatlaf.FlatLightLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLUE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        Image menuTheme;
        try {
            menuTheme = ImageIO.read(Objects.requireNonNull(getClass().getResource("/menu_theme.png"))).getScaledInstance(frame.getContentPane().getWidth(), frame.getContentPane().getHeight(), Image.SCALE_DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Menu menu = new Menu(menuTheme);
        frame.add(menu);

        frame.setVisible(true);
    }

}
