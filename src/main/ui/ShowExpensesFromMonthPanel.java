package ui;

import model.Expense;
import model.ExpenseList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ShowExpensesFromMonthPanel extends ShowExpensesPanel {
    private JLabel text;
    private JButton submit;
    private JComboBox selectMonth;
    private ArrayList<String> months;
    private String month;

    public ShowExpensesFromMonthPanel(int width, int height, ExpenseList expenseList, CardLayout cards,
                                         JPanel cardLayoutPanel) {
        super(width, height, expenseList, cards, cardLayoutPanel);

        this.text = new JLabel("Select a month: ");
        this.text.setBounds(42, 578, 120, 40);
        this.text.setFont(new Font("Monospaced", Font.PLAIN, 12));

        this.selectMonth = new JComboBox();

        this.months = new ArrayList<String>(Arrays.asList("January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November", "December"));

        for (int i = 0; i < 12; i++) {
            this.selectMonth.addItem(this.months.get(i));
        }

        this.selectMonth.setBounds(155, 580, 120, 40);
        this.selectMonth.setForeground(new Color(0, 100, 0));
        this.selectMonth.setFont(new Font("Monospaced", Font.PLAIN, 12));

        this.submit = new JButton("submit");
        this.submit.setBounds(285, 580, 80, 40);
        this.submit.setFont(new Font("Monospaced", Font.PLAIN, 12));

        addActions(expenseList);

        this.add(submit);
        this.add(text);
        this.add(selectMonth);
    }

    private void addActions(ExpenseList expenseList) {
        submit.addActionListener(e -> {
            expenses = expenseList.getExpensesFromMonth(selectMonth.getSelectedIndex() + 1);
            model.clear();
            loadList();

        });
    }
}