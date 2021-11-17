package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUpPanel extends JPanel {
    private JButton start;

    public StartUpPanel(int width, int height, CardLayout cards, JPanel cardLayoutPanel) {
        this.setBackground(new Color(0, 100, 0));
        this.setBounds((int) (width * 0.025), (int) (height * 0.05), (int) (width * 0.95), (int) (height * 0.9));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(null);

        start = new JButton("Start");
        start.setBounds(140, 500, 100, 50);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(cardLayoutPanel, "home page");
            }
        });
        this.add(start);
    }
}
