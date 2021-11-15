package ui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import model.Expense;
import model.ExpenseList;
import persistence.JsonReader;
import persistence.JsonWriter;

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
    private JPanel homePagePanel;
    private JPanel textAreaPanel;

    private CardLayout cards = new CardLayout();

    //EFFECTS: runs the EasyMoney application
    public EasyMoneyAppGUI() throws FileNotFoundException {
        runApp();
    }

    private void runApp() {
        init();

        frame.add(titlePanel);

        cardLayoutPanel.setLayout(cards);

        cardLayoutPanel.add(homePagePanel, "home page");
//        JPanel j1 = new JPanel();
//        j1.setBackground(Color.BLUE);
//        JPanel j2 = new JPanel();
//        j2.setBackground(Color.CYAN);
//
//        cardLayoutPanel.add(j1, "2");
//        cardLayoutPanel.add(j2, "1");

        cards.show(cardLayoutPanel, "home page");
        frame.add(cardLayoutPanel);

//        String text = "";
//        drawTextArea(text);
        frame.setVisible(true);
    }

//    private void drawFrame() {
//        frame = new JFrame();
//        frame.setTitle("EasyMoney App");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
//        frame.setSize(WIDTH, HEIGHT);
//        frame.setLayout(null);
//        frame.getContentPane().setBackground(Color.WHITE);
//    }

//    private void drawTitle() {
//        titlePanel = new JPanel();
//        titlePanel.setBackground(Color.WHITE);
////        titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        titlePanel.setBounds(0, 0, WIDTH, (int) (HEIGHT * 0.05));
//
//        JLabel title = new JLabel();
//        title.setText("EasyMoney");
//        title.setHorizontalAlignment(JLabel.CENTER);
//        title.setVerticalAlignment(SwingConstants.CENTER);
//        title.setForeground(new Color(0, 100, 0));
//        title.setFont(new Font("Monospaced", Font.BOLD, 24));
//
//        titlePanel.add(title);
//        frame.add(titlePanel);
//    }

    private void drawTextArea(String text) {
        textAreaPanel = new JPanel();
        textAreaPanel.setBackground(Color.WHITE);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaces", Font.PLAIN, 15));
        textArea.setEditable(true);

        textArea.append(text);

        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension((int) (WIDTH * 0.95), (int) (HEIGHT * 0.6)));

        textAreaPanel.add(scroll);
        textAreaPanel.setBounds(0, (int) (HEIGHT * 0.05), WIDTH, (int) (HEIGHT * 0.7));
        textAreaPanel.setBorder(BorderFactory.createEmptyBorder());

        frame.add(textAreaPanel);
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
        frame = new Frame(WIDTH, HEIGHT);
        titlePanel = new TitlePanel(WIDTH, HEIGHT);
        cardLayoutPanel = new CardLayoutPanel(WIDTH, HEIGHT);
        homePagePanel = new HomePagePanel(WIDTH, HEIGHT);

        expenseList = new ExpenseList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(DIRECTORY);
        jsonReader = new JsonReader(DIRECTORY);
    }
}

