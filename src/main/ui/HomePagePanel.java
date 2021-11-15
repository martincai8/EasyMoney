package ui;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class HomePagePanel extends JPanel {
    private int width;
    private int height;
    public HomePagePanel(int width, int height) {
        this.width = width;
        this.height = height;

        this.setBackground(Color.WHITE);
        this.setBounds((int) (this.width * 0.04), (int) (this.height * 0.05),
                (int) (this.width * 0.95), (int) (this.height * 0.9));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(null);

        addItems();
    }

    private void addItems() {
        addWelcomeText();
        addMenuButtons();
        addBottomButtons();
    }

    // the code from lines 7-10 of this method are from https://bit.ly/3DfykyB
    private void addWelcomeText() {
        JTextPane welcomeText = new JTextPane();
        welcomeText.setText("Welcome to EasyMoney, please select one of the following options");
        welcomeText.setFont(new Font("Monospaces", Font.PLAIN, 14));
        welcomeText.setBounds((int) (width * 0.08), (int) (height * 0.1), (int) (width * 0.8), (int) (height * 0.05));
        welcomeText.setEditable(false);

        StyledDocument doc = welcomeText.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        this.add(welcomeText);
    }

    private void addMenuButtons() {
        JButton allExpenses = new JButton("view all expenses");
        JButton allExpensesFromCat = new JButton("view all expenses from a category");
        JButton allExpensesFromMonth = new JButton("view all expenses from a month");
        JButton addExpense = new JButton("add new expense");
        JButton deleteExpense = new JButton("delete existing expense");

        allExpenses.setBounds(60, 180, 250, 50);
        allExpensesFromCat.setBounds(60, 260, 250, 50);
        allExpensesFromMonth.setBounds(60, 330, 250, 50);
        addExpense.setBounds(60, 400, 250, 50);
        deleteExpense.setBounds(60, 470, 250, 50);

        this.add(allExpenses);
        this.add(allExpensesFromCat);
        this.add(allExpensesFromMonth);
        this.add(addExpense);
        this.add(deleteExpense);
    }

    private void addBottomButtons() {
        JButton saveFile = new JButton("save file");
        JButton quit = new JButton("quit");

        saveFile.setBounds(20, 650, 100, 50);
        quit.setBounds(260, 650, 100, 50);

        this.add(saveFile);
        this.add(quit);
    }
}
