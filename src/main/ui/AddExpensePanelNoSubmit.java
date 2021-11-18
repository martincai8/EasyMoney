package ui;

import model.Expense;
import model.ExpenseList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class AddExpensePanelNoSubmit extends JPanel {
    private int width;
    private int height;
    private ExpenseList expenseList;
    private CardLayout cards;
    private CardLayoutPanel cardLayoutPanel;

    private JLabel dollarSign;
    private JLabel dollarAmount;
    private JLabel description;
    private JLabel category;
    private JLabel date;
    private JLabel time;
    private JLabel month;
    private JLabel day;
    private JLabel year;

    protected JFormattedTextField dollarField;
    protected JFormattedTextField descriptionField;
    protected JComboBox selectCategory;
    protected JFormattedTextField timeField;
    protected JComboBox selectMonth;
    protected JComboBox selectDay;
    protected JFormattedTextField yearField;
    protected JButton submit;
    protected JButton home;

    public AddExpensePanelNoSubmit(int width, int height, ExpenseList expenseList,
                           CardLayout cards, JPanel cardLayoutPanel) {
        setUpFields();

        this.setBackground(Color.WHITE);
        this.setBounds((int) (width * 0.025), (int) (height * 0.05), (int) (width * 0.95), (int) (height * 0.9));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(null);

        setUpLabels();
        labelsSetBounds();

        setUpInputs();
        inputsSetBounds();

//        this.submit = new JButton("submit");
//        this.submit.setBounds(160, 600, 100, 50);
//        this.submit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Double dollarValue = Double.parseDouble(dollarField.getText());
//                String descriptionValue = (String) descriptionField.getText();
//                String categoryValue = (String) selectCategory.getSelectedItem();
//                String timeValue = (String) timeField.getText();
//                String monthValue = selectMonth.getSelectedItem().toString();
//                Integer dayValue = (int) selectDay.getSelectedIndex() + 1;
//                Integer yearValue = Integer.parseInt(yearField.getText());
//                String dateStr = timeValue + " " + monthValue.toString() + " " + dayValue.toString() + ", "
//                        +
//                        yearValue.toString();
//                LocalDateTime date = stringToDate(dateStr, dayValue);
//                Expense newExpense = new Expense(dollarValue, descriptionValue, categoryValue, date);
//                expenseList.addExpense(newExpense); //FIX
//                System.out.println("Added expense");
//                System.out.println(newExpense);
//            }
//        });

        this.home = new JButton("home");
        this.home.setBounds(160, 650, 100, 50);
        this.home.addActionListener(e -> cards.show(cardLayoutPanel, "home page"));

        addToPanel();
    }

    private void setUpFields() {
        this.width = width;
        this.height = height;
        this.expenseList = expenseList;
        this.cards = cards;
        this.cardLayoutPanel = cardLayoutPanel;
    }

    private void setUpLabels() {
        this.dollarSign = new JLabel("$");
        this.dollarAmount = new JLabel("dollar amount");
        this.description = new JLabel("description");
        this.category = new JLabel("category");
        this.date = new JLabel("date");
        this.time = new JLabel("time");
        this.month = new JLabel("month");
        this.day = new JLabel("day");
        this.year = new JLabel("year");

        setHorizontalAlignment();

        this.dollarSign.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.dollarAmount.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.description.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.category.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.date.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.time.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.month.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.day.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.year.setFont(new Font("Monospaced", Font.PLAIN, 14));

    }

    private void setHorizontalAlignment() {
        this.dollarSign.setHorizontalAlignment(JLabel.CENTER);
        this.dollarAmount.setHorizontalAlignment(JLabel.CENTER);
        this.description.setHorizontalAlignment(JLabel.CENTER);
        this.category.setHorizontalAlignment(JLabel.CENTER);
        this.date.setHorizontalAlignment(JLabel.CENTER);
        this.time.setHorizontalAlignment(JLabel.CENTER);
        this.month.setHorizontalAlignment(JLabel.CENTER);
        this.day.setHorizontalAlignment(JLabel.CENTER);
        this.year.setHorizontalAlignment(JLabel.CENTER);
    }

    private void labelsSetBounds() {
        this.dollarSign.setBounds(160, 80, 20, 30);
        this.dollarAmount.setBounds(140, 50, 140, 15);
        this.description.setBounds(140, 170, 140, 15);
        this.category.setBounds(140, 290, 140, 15);
        this.date.setBounds(140, 410, 140, 15);
        this.time.setBounds(140, 450, 140, 15);
        this.month.setBounds(40, 530, 140, 15);
        this.day.setBounds(140, 530, 140, 15);
        this.year.setBounds(240, 530, 140, 15);
    }

    private void setUpInputs() {
        String[] categories = {"Groceries", "Movies", "Restaurants", "Shopping"};
//        ArrayList<String> months = new ArrayList<String>(Arrays.asList("January", "February", "March", "April", "May",
//                "June", "July", "August", "September", "October", "November", "December"));
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        this.dollarField = new JFormattedTextField();
        this.descriptionField = new JFormattedTextField();
        this.selectCategory = new JComboBox(categories);
        this.timeField = new JFormattedTextField();
        this.selectMonth = new JComboBox(months);
        this.selectDay = new JComboBox();
        for (int i = 1; i <= 31; i++) {
            selectDay.addItem(i);
        }
        this.yearField = new JFormattedTextField();

        this.dollarField.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 1));
        this.descriptionField.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 1));
        this.selectCategory.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0)));
        this.timeField.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 1));
        this.selectMonth.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0)));
        this.selectDay.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0)));
        this.yearField.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 1));
    }

    private void inputsSetBounds() {
        this.dollarField.setBounds(180, 80, 80, 30);
        this.descriptionField.setBounds(140, 210, 140, 30);
        this.selectCategory.setBounds(140, 320, 140, 30);
        this.timeField.setBounds(170, 470, 80, 30);
        this.selectMonth.setBounds(80, 550, 60, 30);
        this.selectDay.setBounds(180, 550, 60, 30);
        this.yearField.setBounds(280, 550, 80, 30);
    }


    private void addToPanel() {
        this.add(dollarSign);
        this.add(dollarAmount);
        this.add(description);
        this.add(category);
        this.add(date);
        this.add(time);
        this.add(month);
        this.add(day);
        this.add(year);

        this.add(dollarField);
        this.add(descriptionField);
        this.add(selectCategory);
        this.add(timeField);
        this.add(selectMonth);
        this.add(selectDay);
        this.add(yearField);

//        this.add(submit);
        this.add(home);

    }

    //REQUIRES: dateStr to be in the format "HH:mm MMM dd, yyyy"
    //EFFECTS: returns a LocalDateTime object parsed from a string
    public LocalDateTime stringToDate(String dateStr, int day) {
        DateTimeFormatter format;
        if (day <= 9) {
            format = DateTimeFormatter.ofPattern("HH:mm MMM d, yyyy");
        } else {
            format = DateTimeFormatter.ofPattern("HH:mm MMM dd, yyyy");
        }
        return LocalDateTime.parse(dateStr, format);
    }

//    public JButton getSubmit() {
//        return submit;
//    }

//    public void submitSetAction(ExpenseList expenseList) {
//        this.submit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Double dollarValue = Double.parseDouble(dollarField.getText());
//                String descriptionValue = (String) descriptionField.getText();
//                String categoryValue = (String) selectCategory.getSelectedItem();
//                String timeValue = (String) timeField.getText();
//                String monthValue = selectMonth.getSelectedItem().toString();
//                Integer dayValue = (int) selectDay.getSelectedIndex() + 1;
//                Integer yearValue = Integer.parseInt(yearField.getText());
//                String dateStr = timeValue + " " + monthValue.toString() + " " + dayValue.toString() + ", "
//                        +
//                        yearValue.toString();
//                LocalDateTime date = stringToDate(dateStr, dayValue);
//                Expense newExpense = new Expense(dollarValue, descriptionValue, categoryValue, date);
//                expenseList.addExpense(newExpense); //FIX
//                System.out.println("Added expense");
//                System.out.println(newExpense);
//            }
//        });
//    }
}
