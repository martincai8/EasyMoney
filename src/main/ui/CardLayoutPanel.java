package ui;

import javax.swing.*;
import java.awt.*;

// Represents the CardLayoutPanel
public class CardLayoutPanel extends JPanel {

    // Constructs a CardLayout Panel
    public CardLayoutPanel(int width, int height) {
        this.setBackground(Color.WHITE);
        this.setBounds((int) (width * 0.025), (int) (height * 0.05), (int) (width * 0.95), (int) (height * 0.9));
        this.setBorder(BorderFactory.createEmptyBorder());
    }
}
