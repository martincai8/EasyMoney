package ui;

import model.Expense;
import model.ExpenseList;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Represents the AddExpensePanel without its submit button
public class AddExpensePanelNoSubmit extends JPanel {
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

    // EFFECT: Constructs the panel with its labels and fields
    public AddExpensePanelNoSubmit(int width, int height, ExpenseList expenseList,
                           CardLayout cards, JPanel cardLayoutPanel) {
        this.setBackground(Color.WHITE);
        this.setBounds((int) (width * 0.025), (int) (height * 0.05), (int) (width * 0.95), (int) (height * 0.9));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(null);

        setUpLabels();
        labelsSetBounds();

        setUpInputs();
        inputsSetBounds();

        this.home = new JButton("home");
        this.home.setBounds(160, 650, 100, 50);
        this.home.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.home.addActionListener(e -> cards.show(cardLayoutPanel, "home page"));

        addToPanel();
    }

    // MODIFIES: dollarSign, dollarAmount, description, category, date, time, month, day, year
    // EFFECTS: adds text to the labels and sets their alignment + font
    private void setUpLabels() {
        this.dollarSign = new JLabel("$");
        this.dollarAmount = new JLabel("dollar amount");
        this.description = new JLabel("description");
        this.category = new JLabel("category");
        this.date = new JLabel("date");
        this.time = new JLabel("24H time");
        this.month = new JLabel("month");
        this.day = new JLabel("day");
        this.year = new JLabel("year");

        setHorizontalAlignment();

        this.dollarAmount.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.description.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.category.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.date.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.time.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.month.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.day.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.year.setFont(new Font("Monospaced", Font.PLAIN, 14));

    }

    // MODIFIES: this
    // EFFECTS: centers the text of the labels horizontally
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

    // MODIFIES: dollarSign, dollarAmount, description, category, date, time, month, day, year
    // EFFECTS: sets the bounds of the labels
    private void labelsSetBounds() {
        this.dollarSign.setBounds(155, 75, 20, 30);
        this.dollarAmount.setBounds(140, 50, 140, 15);
        this.description.setBounds(140, 170, 140, 15);
        this.category.setBounds(140, 290, 140, 15);
        this.date.setBounds(140, 410, 140, 15);
        this.time.setBounds(140, 450, 140, 15);
        this.month.setBounds(35, 530, 140, 15);
        this.day.setBounds(140, 530, 140, 15);
        this.year.setBounds(245, 530, 140, 15);
    }

    // MODIFIES: dollarField, descriptionField, selectCategory, timeField, selectMonth, selectDay, yearField
    // EFFECTS: sets up the text fields and the selection boxes
    private void setUpInputs() {
        String[] categories = {"Groceries", "Movies", "Restaurants", "Shopping"};
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

        this.dollarField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        this.descriptionField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        this.timeField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        this.yearField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        this.selectCategory.setSelectedIndex(-1);
        this.selectMonth.setSelectedIndex(-1);
        this.selectDay.setSelectedIndex(-1);

        setFontsForInputs();
    }

    // MODIFIES: dollarField, descriptionField, selectCategory, timeField, selectMonth, selectDay, yearField
    // EFFECTS: changes the default fonts of the inputs
    private void setFontsForInputs() {
        this.dollarField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.descriptionField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.selectCategory.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.timeField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.selectMonth.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.selectDay.setFont(new Font("Monospaced", Font.PLAIN, 14));
        this.yearField.setFont(new Font("Monospaced", Font.PLAIN, 14));
    }

    // MODIFIES: dollarField, descriptionField, selectCategory, timeField, selectMonth, selectDay, yearField
    // EFFECTS: ets the bounds of the inputs
    private void inputsSetBounds() {
        this.dollarField.setBounds(190, 75, 50, 30);
        this.descriptionField.setBounds(140, 205, 140, 30);
        this.selectCategory.setBounds(140, 315, 140, 30);
        this.timeField.setBounds(185, 465, 50, 30);
        this.selectMonth.setBounds(60, 545, 90, 30);
        this.selectDay.setBounds(175, 545, 70, 30);
        this.yearField.setBounds(295, 545, 40, 30);
    }

    // MODIFIES: this
    // EFFECTS: adds all the elements to the panel
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

        this.add(home);

    }

    //REQUIRES: dateStr to be in the format "HH:mm MMM dd, yyyy" or "HH:mm MMM d, yyyy"
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
}
