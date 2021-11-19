package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUpPanelNoStartButton extends JPanel {
    private ImageIcon img;
    private JLabel imgLabel;

    public StartUpPanelNoStartButton(int width, int height, CardLayout cards, JPanel cardLayoutPanel) {
        this.setBackground(Color.WHITE);
        this.setBounds((int) (width * 0.025), (int) (height * 0.05), (int) (width * 0.95), (int) (height * 0.9));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(null);

        img = new ImageIcon("./data/startUp.gif");
        this.imgLabel = new JLabel(img);

        this.imgLabel.setBounds(0, 90, width, 400);

        this.add(imgLabel);

    }
}
