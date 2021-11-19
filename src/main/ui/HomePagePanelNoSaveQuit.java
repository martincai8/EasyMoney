package ui;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

//Represents the HomePagePanel without the save and quit buttons
public class HomePagePanelNoSaveQuit extends JPanel {
    private int width;
    private int height;
    private JButton allExpenses;
    private JButton allExpensesFromCat;
    private JButton allExpensesFromMonth;
    private JButton addExpense;
    private JButton deleteExpense;
    private CardLayout cards;
    private JPanel cardLayoutPanel;

    //Constructs a HomePagePanel without the save and quit buttons
    public HomePagePanelNoSaveQuit(int width, int height, CardLayout cards, JPanel cardLayoutPanel) {
        this.width = width;
        this.height = height;
        this.cards = cards;
        this.cardLayoutPanel = cardLayoutPanel;

        this.setBackground(Color.WHITE);
        this.setBounds((int) (this.width * 0.04), (int) (this.height * 0.05),
                (int) (this.width * 0.95), (int) (this.height * 0.9));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(null);

        addItems();
    }

    // MODIFIES: this
    // EFFECTS: adds the welcome text and menu buttons to the panel
    private void addItems() {
        addWelcomeText();
        addMenuButtons();
    }

    // MODIFIES: this
    // EFFECTS: sets up the welcomeText label and adds it to the panel
    // the code from lines 7-10 of this method are from https://bit.ly/3DfykyB
    private void addWelcomeText() {
        JTextPane welcomeText = new JTextPane();
        welcomeText.setText("Welcome to EasyMoney, please select one of the following options");
        welcomeText.setFont(new Font("Monospaced", Font.PLAIN, 14));
        welcomeText.setBounds((int) (width * 0.08), (int) (height * 0.1), (int) (width * 0.8), (int) (height * 0.05));
        welcomeText.setEditable(false);

        StyledDocument doc = welcomeText.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        this.add(welcomeText);
    }

    // MODIFIES: this
    // EFFECTS: sets up the menu buttons and adds them to the panel
    private void addMenuButtons() {
        allExpenses = new JButton("view all expenses");
        allExpensesFromCat = new JButton("view all expenses from a category");
        allExpensesFromMonth = new JButton("view all expenses from a month");
        addExpense = new JButton("add new expense");
        deleteExpense = new JButton("delete existing expense");

        menuButtonsSetUp();

        allExpenses.addActionListener(e -> cards.show(cardLayoutPanel, "all expenses"));
        allExpensesFromCat.addActionListener(e -> cards.show(cardLayoutPanel, "expenses from category"));
        allExpensesFromMonth.addActionListener(e -> cards.show(cardLayoutPanel, "expenses from month"));
        addExpense.addActionListener(e -> cards.show(cardLayoutPanel, "add expense"));
        deleteExpense.addActionListener(e -> cards.show(cardLayoutPanel, "delete expense"));

        this.add(allExpenses);
        this.add(allExpensesFromCat);
        this.add(allExpensesFromMonth);
        this.add(addExpense);
        this.add(deleteExpense);
    }

    // MODIFIES: allExpenses, allExpensesFromCat, allExpensesFromMonth, addExpense, deleteExpense
    // EFFECTS: sets the bounds and fonts for the buttons
    private void menuButtonsSetUp() {
        allExpenses.setBounds(80, 160, 250, 50);
        allExpensesFromCat.setBounds(80, 250, 250, 50);
        allExpensesFromMonth.setBounds(80, 340, 250, 50);
        addExpense.setBounds(80, 430, 250, 50);
        deleteExpense.setBounds(80, 520, 250, 50);

        allExpenses.setFont(new Font("Monospaced", Font.PLAIN, 12));
        allExpensesFromCat.setFont(new Font("Monospaced", Font.PLAIN, 12));
        allExpensesFromMonth.setFont(new Font("Monospaced", Font.PLAIN, 12));
        addExpense.setFont(new Font("Monospaced", Font.PLAIN, 12));
        deleteExpense.setFont(new Font("Monospaced", Font.PLAIN, 12));
    }
}
