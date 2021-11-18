package ui;

import model.ExpenseList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class ShowExpensesFromCategoryPanel extends ShowExpensesPanel {
    private JButton submit;
    private JComboBox selectCategory;
    private JLabel text;

    public ShowExpensesFromCategoryPanel(int width, int height, ExpenseList expenseList, CardLayout cards,
                                         JPanel cardLayoutPanel) {
        super(width, height, expenseList, cards, cardLayoutPanel);

        this.text = new JLabel("Select a category:");
        this.text.setBounds(25, 578, 140, 40);
        this.text.setFont(new Font("Monospaced", Font.PLAIN, 12));

        String[] categories = {"Groceries", "Movies", "Restaurants", "Shopping"};

        this.selectCategory = new JComboBox(categories);
        this.selectCategory.setBounds(160, 580, 140, 40);
        this.selectCategory.setForeground(new Color(0, 100, 0));
        this.selectCategory.setFont(new Font("Monospaced", Font.PLAIN, 12));

        this.submit = new JButton("submit");
        this.submit.setBounds(310, 580, 80, 40);
        this.submit.setFont(new Font("Monospaced", Font.PLAIN, 12));

        addActions(expenseList);

        this.add(text);
        this.add(submit);
        this.add(selectCategory);
    }

    private void addActions(ExpenseList expenseList) {
        selectCategory.addActionListener(e -> System.out.println(selectCategory.getSelectedItem()));
        submit.addActionListener(e -> {
            expenses = expenseList.getExpensesFromCategory(selectCategory.getSelectedItem().toString());
            model.clear();
            loadList();
        });
    }
}
