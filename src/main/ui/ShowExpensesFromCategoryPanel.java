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

        text = new JLabel("Select a category: ");
        text.setBounds(40, 578, 120, 40);

        submit = new JButton("submit");
        submit.setBounds(290, 580, 80, 40);

        String[] categories = {"Groceries", "Movies", "Restaurants", "Shopping"};

        selectCategory = new JComboBox(categories);
        selectCategory.setBounds(160, 580, 120, 40);
        selectCategory.setForeground(Color.BLUE);

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
