package launcher;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private Image backgroundImage;

    public Menu(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        this.setLayout(null);
        this.setSize(1280, 720);
        this.setBackground(Color.BLUE);

        //New Game button
        JButton newGameButton = new JButton("New Game");
        newGameButton.setBackground(Color.GRAY);
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setSize(300,100);
        newGameButton.setLocation(100, 100);

        this.add(newGameButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}
