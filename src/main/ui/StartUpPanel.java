package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUpPanel extends JPanel {
    public StartUpPanel(int width, int height) {
        this.setBackground(new Color(0, 100, 0));
        this.setBounds((int) (width * 0.025), (int) (height * 0.05), (int) (width * 0.95), (int) (height * 0.9));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(null);

        JButton login = new JButton("Start");
        login.setBounds(140, 500, 100, 50);

        this.add(login);
    }
}
