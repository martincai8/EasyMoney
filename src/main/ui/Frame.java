package ui;

import javax.swing.*;
import java.awt.*;

// Represents a Frame
public class Frame extends JFrame {

    //Constructs a Frame with its width and height
    public Frame(int width, int height) {
        this.setTitle("EasyMoney App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(width, height);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.WHITE);
    }
}
