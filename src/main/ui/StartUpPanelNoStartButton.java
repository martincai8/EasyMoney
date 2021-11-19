package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUpPanelNoStartButton extends JPanel {
//    private JButton start;

    public StartUpPanelNoStartButton(int width, int height, CardLayout cards, JPanel cardLayoutPanel) {
        this.setBackground(new Color(0, 100, 0));
        this.setBounds((int) (width * 0.025), (int) (height * 0.05), (int) (width * 0.95), (int) (height * 0.9));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(null);

//        this.start = new JButton("start");
//        this.start.setBounds(160, 500, 100, 50);
//        this.start.setFont(new Font("Monospaced", Font.PLAIN, 14));
//
//        start.addActionListener(e -> cards.show(cardLayoutPanel, "home page"));
//        this.add(start);
    }
}
