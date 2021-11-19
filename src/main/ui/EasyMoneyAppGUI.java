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
    public static final int WIDTH = 440;
    public static final int HEIGHT = 800;

    private static final String DIRECTORY = "./data/defaultExpenseList.json";
    private ExpenseList expenseList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Frame frame;
    private JPanel titlePanel;
    private JPanel cardLayoutPanel;
    private StartUpPanel startUpPanel;
    private JPanel homePagePanel;
    private ShowExpensesPanel showExpensesPanel;
    private ShowExpensesFromCategoryPanel showExpensesFromCategoryPanel;
    private ShowExpensesFromMonthPanel showExpensesFromMonthPanel;
    private AddExpensePanel addExpensePanel;
    private DeleteExpensePanel deleteExpensePanel;

    private JButton start;
    private JButton allExpenses;
    private JButton allExpensesFromCat;
    private JButton allExpensesFromMonth;
    private JButton addExpense;
    private JButton deleteExpense;
    private JButton saveFile;
    private JButton quit;
    private JButton submit;

    private CardLayout cards;

    //EFFECTS: runs the EasyMoney application
    public EasyMoneyAppGUI() throws FileNotFoundException {
        runApp();
    }

    private void runApp() {
        init();

        frame.add(titlePanel);
        cardLayoutPanel.setLayout(cards);
        cardLayoutPanel.add(startUpPanel, "start up page");
//        cardLayoutPanel.add(homePagePanel, "home page");
//        cardLayoutPanel.add(showExpensesPanel, "all expenses");
//        cardLayoutPanel.add(showExpensesFromCategoryPanel, "expenses from category");
//        cardLayoutPanel.add(showExpensesFromMonthPanel, "expenses from month");
//        cardLayoutPanel.add(addExpensePanel, "add expense");
//        cardLayoutPanel.add(deleteExpensePanel, "delete expense");

        cards.show(cardLayoutPanel, "start up page");
        frame.add(cardLayoutPanel);

        frame.setVisible(true);
    }

    class AddExpensePanel extends AddExpensePanelNoSubmit {
        public AddExpensePanel(int width, int height, ExpenseList expenseList2,
                               CardLayout cards, JPanel cardLayoutPanel) {
            super(width, height, expenseList2, cards, cardLayoutPanel);
            this.submit = new JButton("add expense");
            this.submit.setBounds(160, 600, 100, 50);
            this.submit.setFont(new Font("Monospaced", Font.PLAIN, 12));
            this.submit.addActionListener(e -> {
                Double dollarValue = Double.parseDouble(dollarField.getText());
                String descriptionValue = (String) descriptionField.getText();
                String categoryValue = (String) selectCategory.getSelectedItem();
                String timeValue = (String) timeField.getText();
                String monthValue = selectMonth.getSelectedItem().toString();
                Integer dayValue = (int) selectDay.getSelectedIndex() + 1;
                Integer yearValue = Integer.parseInt(yearField.getText());
                String dateStr = timeValue + " " + monthValue + " " + dayValue + ", " + yearValue;
                LocalDateTime date = stringToDate(dateStr, timeValue, dayValue);
                Expense newExpense = new Expense(dollarValue, descriptionValue, categoryValue, date);
                expenseList.addExpense(newExpense);
                showExpensesPanel.refresh(expenseList.getAllExpenses());
                showExpensesFromCategoryPanel.refresh(expenseList.getAllExpenses());
                showExpensesFromMonthPanel.refresh(expenseList.getAllExpenses());
                refreshPanel();
            });
            this.add(submit);
        }

        private void refreshPanel() {
            List<JFormattedTextField> fieldList = new ArrayList<>(Arrays.asList(
                    dollarField, descriptionField, timeField, yearField));
            List<JComboBox> boxList = new ArrayList<>(Arrays.asList(selectCategory, selectMonth, selectDay));

            for (JFormattedTextField field : fieldList) {
                field.setText("");
            }

            for (JComboBox box : boxList) {
                box.setSelectedIndex(-1);
            }
        }
    }

    class DeleteExpensePanel extends ShowExpensesPanel {
        private JButton deleteButton;

        public DeleteExpensePanel(int width, int height, ExpenseList expenseList,
                                  CardLayout cards, JPanel cardLayoutPanel) {
            super(width, height, expenseList, cards, cardLayoutPanel);

//            this.deleteLabel = new JLabel("enter the row # of the item you want to delete");
//            this.deleteLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
//            this.deleteLabel.setBounds(40, 450, 300, 15);

            this.deleteButton = new JButton("delete");
            this.deleteButton.setBounds(160, 600, 100, 50);
            this.deleteButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
            this.deleteButton.addActionListener(e -> {
                String expense = jlist.getSelectedValue().toString();
                expenseList.removeExpense(jlist.getSelectedValue());
                this.refresh(expenseList.getAllExpenses());
                showExpensesPanel.refresh(expenseList.getAllExpenses());
                showExpensesFromCategoryPanel.refresh(expenseList.getAllExpenses());
                showExpensesFromMonthPanel.refresh(expenseList.getAllExpenses());
            });

            this.add(deleteButton);
        }
    }

    class StartUpPanel extends StartUpPanelNoStartButton {
        private JButton start;

        public StartUpPanel(int width, int height, CardLayout cards, JPanel cardLayoutPanel) {
            super(width, height, cards, cardLayoutPanel);

            this.start = new JButton("start");
            this.start.setBounds(160, 500, 100, 50);
            this.start.setFont(new Font("Monospaced", Font.PLAIN, 14));

            this.start.addActionListener(e -> {
                int option = JOptionPane.showConfirmDialog(frame, "do you want to load your data?",
                        "", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    loadFile();
                    System.out.println("loaded file");
                }
                initOtherPanels();
                cards.show(cardLayoutPanel, "home page");
            });
            this.add(start);
        }

//        public void askLoad() {
//
//        }
    }

    class HomePagePanel extends HomePagePanelNoSaveQuit {
        private JButton save;
        private JButton quit;

        public HomePagePanel(int width, int height, CardLayout cards, JPanel cardLayoutPanel) {
            super(width, height, cards, cardLayoutPanel);

            this.save = new JButton("save file");
            this.quit = new JButton("quit");

            setBounds();

            addActions();

            this.add(this.save);
            this.add(this.quit);
        }

        private void addActions() {
            this.save.addActionListener(e -> {
                saveFile();
                // might need to refresh here
            });
            this.quit.addActionListener(e -> {
                int option = JOptionPane.showConfirmDialog(frame, "do you want to overwrite your saved data?",
                        "", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    saveFile();
                }
                frame.dispose();
            });
        }

        private void setBounds() {
            this.save.setBounds(40, 650, 100, 50);
            this.quit.setBounds(280, 650, 100, 50);
//            this.load.setBounds(160, 650, 100, 50);
            this.save.setFont(new Font("Monospaced", Font.PLAIN, 12));
            this.quit.setFont(new Font("Monospaced", Font.PLAIN, 12));
//            this.load.setFont(new Font("Monospaced", Font.PLAIN, 12));
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
            this.expenseList = jsonReader.read();
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

        cards = new CardLayout();
        frame = new Frame(WIDTH, HEIGHT);
        titlePanel = new TitlePanel(WIDTH, HEIGHT);
        cardLayoutPanel = new CardLayoutPanel(WIDTH, HEIGHT);
        startUpPanel = new StartUpPanel(WIDTH, HEIGHT, cards, cardLayoutPanel);
    }

    private void initOtherPanels() {
        homePagePanel = new HomePagePanel(WIDTH, HEIGHT, cards, cardLayoutPanel);
        showExpensesPanel = new ShowExpensesPanel(WIDTH, HEIGHT, expenseList, cards, cardLayoutPanel);
        showExpensesFromCategoryPanel = new ShowExpensesFromCategoryPanel(WIDTH, HEIGHT,
                expenseList, cards, cardLayoutPanel);
        showExpensesFromMonthPanel = new ShowExpensesFromMonthPanel(WIDTH, HEIGHT, expenseList, cards, cardLayoutPanel);
        addExpensePanel = new AddExpensePanel(WIDTH, HEIGHT, expenseList, cards, cardLayoutPanel);
        deleteExpensePanel = new DeleteExpensePanel(WIDTH, HEIGHT, expenseList, cards, cardLayoutPanel);

        cardLayoutPanel.add(homePagePanel, "home page");
        cardLayoutPanel.add(showExpensesPanel, "all expenses");
        cardLayoutPanel.add(showExpensesFromCategoryPanel, "expenses from category");
        cardLayoutPanel.add(showExpensesFromMonthPanel, "expenses from month");
        cardLayoutPanel.add(addExpensePanel, "add expense");
        cardLayoutPanel.add(deleteExpensePanel, "delete expense");
    }
}

