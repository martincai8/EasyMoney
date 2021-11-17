package ui;

import model.Expense;
import model.ExpenseList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShowExpensesFromMonthPanel extends ShowExpensesPanel {
    private JLabel text;
    private JButton submit;
    private JComboBox selectMonth;
    private ArrayList<String> months;
    private String month;

    public ShowExpensesFromMonthPanel(int width, int height, ExpenseList expenseList, CardLayout cards,
                                         JPanel cardLayoutPanel) {
        super(width, height, expenseList, cards, cardLayoutPanel);

        text = new JLabel("Select a month: ");
        text.setBounds(20, 580, 120, 40);

        selectMonth = new JComboBox();

        months = new ArrayList<String>(Arrays.asList("January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November", "December"));

        for (int i = 0; i < 12; i++) {
            selectMonth.addItem(months.get(i));
        }

        selectMonth.setBounds(140, 580, 120, 40);
        selectMonth.setForeground(Color.BLUE);

        submit = new JButton("submit");
        submit.setBounds(270, 580, 80, 40);

        addActions(expenseList);

        this.add(submit);
        this.add(text);
        this.add(selectMonth);
    }

    private void addActions(ExpenseList expenseList) {
        selectMonth.addActionListener(e -> System.out.println(selectMonth.getSelectedItem()));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expenses = expenseList.getExpensesFromMonth(selectMonth.getSelectedIndex() + 1);
                model.clear();
                loadList();

            }
        });
    }
}