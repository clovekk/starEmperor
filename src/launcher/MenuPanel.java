package launcher;

import javax.swing.*;
import java.awt.*;

/**
 * the panel in the launcher window
 */
public class MenuPanel extends JPanel {
    private Image backgroundImage;

    public MenuPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        this.setLayout(null);
        this.setSize(1280, 720);
        this.setBackground(Color.BLUE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}
