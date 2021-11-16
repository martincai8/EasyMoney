package ui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.*;
import model.Expense;
import model.ExpenseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

//EasyMoney application
//This class references code from https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class EasyMoneyAppGUI extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 800;

    private static final String DIRECTORY = "./data/defaultExpenseList.json";
    private ExpenseList expenseList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JFrame frame;
    private JPanel titlePanel;
    private JPanel cardLayoutPanel;
    private JPanel startUpPanel;
    private JPanel homePagePanel;
    private JPanel showExpensesPanel;
    private JPanel textAreaPanel;

    private JButton start;
    private JButton allExpenses;
    private JButton allExpensesFromCat;
    private JButton allExpensesFromMonth;
    private JButton addExpense;
    private JButton deleteExpense;
    private JButton saveFile;
    private JButton quit;

    private JList<Expense> jList;
    private DefaultListModel<Expense> model;

    private CardLayout cards = new CardLayout();

    //EFFECTS: runs the EasyMoney application
    public EasyMoneyAppGUI() throws FileNotFoundException {
        runApp();
    }

    private void runApp() {
        init();

        frame.add(titlePanel);
        cardLayoutPanel.setLayout(cards);
        cardLayoutPanel.add(startUpPanel, "start up page");
        cardLayoutPanel.add(homePagePanel, "home page");
        cardLayoutPanel.add(showExpensesPanel, "all expenses");

        cards.show(cardLayoutPanel, "start up page");
        frame.add(cardLayoutPanel);

//        String text = "";
//        drawTextArea(text);
        frame.setVisible(true);
    }

    class Frame extends JFrame {
        public Frame(int width, int height) {
            this.setTitle("EasyMoney App");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setSize(width, height);
            this.setLayout(null);
            this.getContentPane().setBackground(Color.WHITE);
        }
    }

    class CardLayoutPanel extends JPanel {
        public CardLayoutPanel(int width, int height) {
            this.setBackground(Color.WHITE);
            this.setBounds((int) (width * 0.025), (int) (height * 0.05), (int) (width * 0.95), (int) (height * 0.9));
            this.setBorder(BorderFactory.createEmptyBorder());
        }
    }

    class TitlePanel extends JPanel {
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

    class StartUpPanel extends JPanel {
//        private JButton start;

        public StartUpPanel(int width, int height) {
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

    class HomePagePanel extends JPanel {
        private int width;
        private int height;
//        private JButton allExpenses;
//        private JButton allExpensesFromCat;
//        private JButton allExpensesFromMonth;
//        private JButton addExpense;
//        private JButton deleteExpense;

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
            welcomeText.setBounds((int) (width * 0.08), (int) (height * 0.1),
                    (int) (width * 0.8), (int) (height * 0.05));
            welcomeText.setEditable(false);

            StyledDocument doc = welcomeText.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            this.add(welcomeText);
        }

        private void addMenuButtons() {
            allExpenses = new JButton("view all expenses");
            allExpensesFromCat = new JButton("view all expenses from a category");
            allExpensesFromMonth = new JButton("view all expenses from a month");
            addExpense = new JButton("add new expense");
            deleteExpense = new JButton("delete existing expense");

            allExpenses.setBounds(60, 180, 250, 50);
            allExpensesFromCat.setBounds(60, 250, 250, 50);
            allExpensesFromMonth.setBounds(60, 320, 250, 50);
            addExpense.setBounds(60, 390, 250, 50);
            deleteExpense.setBounds(60, 460, 250, 50);

            allExpenses.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cards.show(cardLayoutPanel, "all expenses");
                }
            });
            this.add(allExpenses);
            this.add(allExpensesFromCat);
            this.add(allExpensesFromMonth);
            this.add(addExpense);
            this.add(deleteExpense);
        }

        private void addBottomButtons() {
            saveFile = new JButton("save file");
            quit = new JButton("quit");

            saveFile.setBounds(20, 650, 100, 50);
            quit.setBounds(260, 650, 100, 50);

            this.add(saveFile);
            this.add(quit);
        }
    }

    class ShowExpensesPanel extends JPanel {
        public ShowExpensesPanel(int width, int height) {
            this.setBackground(Color.WHITE);

            jList = new JList<>();
            model = new DefaultListModel<>();

            loadFile();
            List<Expense> expenses = expenseList.getAllExpenses();

            for (Expense e : expenses) {
                model.addElement(e);
            }
            jList.setModel(model);
            jList.setSelectedIndex(0);

            JScrollPane scroll = new JScrollPane(jList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setPreferredSize(new Dimension((int) (width * 0.95), (int) (height * 0.6)));

            this.add(scroll);
            this.setBounds((int) (width * 0.025), (int) (height * 0.05), (int) (width * 0.95), (int) (height * 0.9));
            this.setBorder(BorderFactory.createEmptyBorder());
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runEasyMoney() {
        boolean keepGoing = true;
        String option;
        String save;

        init();
        displayWelcome();
        loadFile();

        while (keepGoing) {
            displayMenu();
            System.out.print("Command: ");
            option = input.next().toLowerCase();

            if (option.equals("q")) {
                System.out.print("Would you like to save your current expenses? y/n ");
                save = input.next();
                if (save.equals("y")) {
                    saveFile();
                }
                System.out.println("Thank you for using EasyMoney, have a great day!");
                keepGoing = false;
            } else {
                processOption(option);
            }
            System.out.print("\n");

        }
    }

    //EFFECTS: saves expenses to file
    private void saveFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(expenseList);
            jsonWriter.close();
            System.out.println("Saved expenses to " + DIRECTORY);
        } catch (FileNotFoundException e) {
            System.out.println("There was an error saving your file.");
        }
    }

    //MODIFIES: this
    //EFFECTS: loads expenses from file
    private void loadFile() {

        try {
            expenseList = jsonReader.read();
            System.out.println("Loaded expenses from " + DIRECTORY);
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }

    //EFFECTS: displays the welcome title
    private void displayWelcome() {
        System.out.println("Welcome to EasyMoney!");
        System.out.println("***********************");
    }

    //EFFECTS: displays the menu options for the user to choose from
    private void displayMenu() {
        System.out.println("Please pick from one of the following options:");
        System.out.println("1 -> view all expenses");
        System.out.println("2 -> view all expenses from a certain category");
        System.out.println("3 -> view all expenses from a certain month");
        System.out.println("4 -> add a new expense");
        System.out.println("5 -> delete an existing expense");
        System.out.println("s -> save file");
        System.out.println("q -> quit");
    }

    //MODIFIES: this
    //EFFECTS: processes the user's selected option
    private void processOption(String opt) {
        String option = opt;

        if (option.equals("1")) {
            viewAllExpenses();
        } else if (option.equals("2")) {
            viewAllExpensesFromCategory();
        } else if (option.equals("3")) {
            viewAllExpensesFromMonth();
        } else if (option.equals("4")) {
            addNewExpense();
        } else if (option.equals("5")) {
            deleteExpense();
        } else if (option.equals("s")) {
            saveFile();
        } else {
            System.out.println("Please try again: ");
            option = input.next().toLowerCase();
            processOption(option);
        }
    }

    //EFFECTS: displays all the user's logged expenses
    private void viewAllExpenses() {
        List<Expense> expenses = expenseList.getAllExpenses();
        System.out.println("Here are all your expenses:");
        displayExpenses(expenses);
    }

    //EFFECTS: displays all the user's logged expenses from a specific category
    private void viewAllExpensesFromCategory() {
        String category;
        List<Expense> expenses;

        System.out.print("Enter a category: ");
        category = input.next();
        expenses = expenseList.getExpensesFromCategory(category);
        System.out.println("Here are your expenses from " + category);
        displayExpenses(expenses);
    }

    //EFFECTS: displays all the user's logged expenses from a specific month
    private void viewAllExpensesFromMonth() {
        int month;
        List<Expense> expenses;

        System.out.print("Enter a month (1-12): ");
        month = input.nextInt();
        while (month < 1 || month > 12) {
            System.out.println("Please enter a valid month value!");
            System.out.print("Enter a month (1-12): ");
            month = input.nextInt();
        }
        expenses = expenseList.getExpensesFromMonth(month);
        System.out.println("Here are your expenses from " + Month.of(month) + ":");
        displayExpenses(expenses);
    }

    //MODIFIES: this
    //EFFECTS: adds a new expense to expenseList
    private void addNewExpense() {
        double amount;
        String description;
        String category;
        String dateStr;
        LocalDateTime date;

        System.out.print("Enter the dollar amount of the expense: $");
        amount = input.nextDouble();

        System.out.print("Enter a description of the expense: ");
        description = input.next();

        System.out.print("Enter the category that this expense belongs to: ");
        category = input.next();

        System.out.print("Enter the date and time of this expense (e.g., \"09:30 Oct 12, 2021\"): ");
        dateStr = input.next();
        date = stringToDate(dateStr);

        Expense newExpense = new Expense(amount,description,category,date);

        expenseList.addExpense(newExpense);

        System.out.println("Successfully added:");
        System.out.println(newExpense);
    }

    //MODIFIES: this
    //EFFECTS: deletes an existing expense from expenseList
    public void deleteExpense() {
        int month;
        List<Expense> expenses;
        int position;
        Expense target;

        System.out.print("Enter the month that this expense is from (1-12): ");
        month = input.nextInt();

        expenses = expenseList.getExpensesFromMonth(month);
        displayExpenses(expenses);

        System.out.print("Which expense would you like to remove? ");
        position = input.nextInt();

        target = expenseList.removeExpense(expenses.get(position - 1));

        System.out.println("Successfully deleted:");
        System.out.println(target.toString());
    }

    //EFFECTS: displays a given list of expenses
    private void displayExpenses(List<Expense> expenses) {
        if (expenses.size() == 0) {
            System.out.println("You don't have any expenses, add one!");
        } else {
            System.out.printf("\n%-4s %-10s %-25s %-20s %s \n", " ", "Amount", "Description", "Category", "Date");
            for (int i = 0; i < expenses.size(); i++) {
                System.out.printf("%-4s %s\n", (i + 1 + "."), expenses.get(i).toString());
            }
        }
    }

    //REQUIRES: dateStr to be in the format "HH:mm MMM dd, yyyy"
    //EFFECTS: returns a LocalDateTime object parsed from a string
    public LocalDateTime stringToDate(String dateStr) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm MMM dd, yyyy");
        return LocalDateTime.parse(dateStr, format);
    }

    // MODIFIES: this
    // EFFECTS: initializes expenseList and adds sample expenses
    private void init() {
        expenseList = new ExpenseList();
        jsonWriter = new JsonWriter(DIRECTORY);
        jsonReader = new JsonReader(DIRECTORY);

        frame = new Frame(WIDTH, HEIGHT);
        titlePanel = new TitlePanel(WIDTH, HEIGHT);
        cardLayoutPanel = new CardLayoutPanel(WIDTH, HEIGHT);
        startUpPanel = new StartUpPanel(WIDTH, HEIGHT);
        homePagePanel = new HomePagePanel(WIDTH, HEIGHT);
        showExpensesPanel = new ShowExpensesPanel(WIDTH, HEIGHT);


    }
}

