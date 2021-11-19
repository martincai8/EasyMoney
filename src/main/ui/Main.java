package ui;

import java.io.FileNotFoundException;

// Represents application's main window frame.
public class Main {
    public static void main(String[] args) {
        try {
            new EasyMoneyAppGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Error: file was not found");
        }
    }
}
