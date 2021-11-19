package ui;

import javax.swing.*;
import java.awt.*;

// Represents a TitlePanel
public class TitlePanel extends JPanel {

    // Constructs a TitlePanel
    public TitlePanel(int width, int height) {
        this.setBackground(Color.WHITE);
        this.setBounds(0, 0, width, (int) (height * 0.05));

        JLabel title = new JLabel();
        title.setText("EasyMoney");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(0, 100, 0));
        title.setFont(new Font("Monospaced", Font.BOLD, 24));

        this.add(title);
    }
}
