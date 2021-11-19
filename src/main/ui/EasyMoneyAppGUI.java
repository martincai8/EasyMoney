package ui;

import javax.swing.*;

import java.awt.*;
import model.Expense;
import model.ExpenseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
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
    private TitlePanel titlePanel;
    private CardLayoutPanel cardLayoutPanel;
    private StartUpPanel startUpPanel;
    private HomePagePanel homePagePanel;
    private ShowExpensesPanel showExpensesPanel;
    private ShowExpensesFromCategoryPanel showExpensesFromCategoryPanel;
    private ShowExpensesFromMonthPanel showExpensesFromMonthPanel;
    private AddExpensePanel addExpensePanel;
    private DeleteExpensePanel deleteExpensePanel;

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
                String descriptionValue = descriptionField.getText();
                String categoryValue = (String) selectCategory.getSelectedItem();
                String timeValue = timeField.getText();
                String monthValue = (String) selectMonth.getSelectedItem();
                Integer dayValue = selectDay.getSelectedIndex() + 1;
                Integer yearValue = Integer.parseInt(yearField.getText());
                String dateStr = timeValue + " " + monthValue + " " + dayValue + ", " + yearValue;
                LocalDateTime date = stringToDate(dateStr, timeValue, dayValue);
                Expense newExpense = new Expense(dollarValue, descriptionValue, categoryValue, date);
                expenseList.addExpense(newExpense);
                showExpensesPanel.refresh(expenseList.getAllExpenses());
                showExpensesFromCategoryPanel.refresh(expenseList.getAllExpenses());
                showExpensesFromMonthPanel.refresh(expenseList.getAllExpenses());
                deleteExpensePanel.refresh(expenseList.getAllExpenses());
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

            this.deleteButton = new JButton("delete");
            this.deleteButton.setBounds(160, 600, 100, 50);
            this.deleteButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
            this.deleteButton.addActionListener(e -> {
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
        private ImageIcon saveIcon;

        public StartUpPanel(int width, int height, CardLayout cards, JPanel cardLayoutPanel) {
            super(width, height, cards, cardLayoutPanel);

            this.start = new JButton("start");
            this.start.setBounds(160, 550, 100, 50);
            this.start.setFont(new Font("Monospaced", Font.PLAIN, 14));

            this.saveIcon = new ImageIcon(new ImageIcon("./data/save icon.png").getImage().getScaledInstance(
                    30, 30, Image.SCALE_DEFAULT));

            this.start.addActionListener(e -> {
                int option = JOptionPane.showConfirmDialog(frame, "do you want to load your data?",
                        "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, saveIcon);
                if (option == 0) {
                    loadFile();
                }
                initOtherPanels();
                cards.show(cardLayoutPanel, "home page");
            });
            this.add(start);
        }
    }

    class HomePagePanel extends HomePagePanelNoSaveQuit {
        private JButton save;
        private JButton quit;
        private ImageIcon saveIcon;

        public HomePagePanel(int width, int height, CardLayout cards, JPanel cardLayoutPanel) {
            super(width, height, cards, cardLayoutPanel);

            this.save = new JButton("save file");
            this.quit = new JButton("quit");
            this.saveIcon = new ImageIcon(new ImageIcon("./data/save icon.png").getImage().getScaledInstance(
                    30, 30, Image.SCALE_DEFAULT));

            setBounds();
            addActions();

            this.add(this.save);
            this.add(this.quit);
        }

        private void addActions() {
            this.save.addActionListener(e -> {
                saveFile();
            });
            this.quit.addActionListener(e -> {
                int option = JOptionPane.showConfirmDialog(frame, "do you want to overwrite your saved data?",
                        "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, this.saveIcon);
                if (option == 0) {
                    saveFile();
                }
                frame.dispose();
            });
        }

        private void setBounds() {
            this.save.setBounds(40, 650, 100, 50);
            this.quit.setBounds(280, 650, 100, 50);

            this.save.setFont(new Font("Monospaced", Font.PLAIN, 12));
            this.quit.setFont(new Font("Monospaced", Font.PLAIN, 12));
        }

    }

    //EFFECTS: saves expenses to file
    private void saveFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(expenseList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("There was an error saving your file.");
        }
    }

    //MODIFIES: this
    //EFFECTS: loads expenses from file
    private void loadFile() {
        try {
            this.expenseList = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
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

