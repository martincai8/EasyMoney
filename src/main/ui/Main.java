package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new EasyMoneyApp();
        } catch (FileNotFoundException e) {
            System.out.println("Error: file was not found");
        }
    }
}
