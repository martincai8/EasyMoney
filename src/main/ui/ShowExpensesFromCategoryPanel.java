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
    public ShowExpensesFromCategoryPanel(int width, int height, ExpenseList expenseList, CardLayout cards,
                                         JPanel cardLayoutPanel) {
        super(width, height, expenseList, cards, cardLayoutPanel);

        submit = new JButton("submit");
        submit.setBounds(220, 580, 80, 40);

        String[] categories = {"Groceries", "Movies", "Restaurants", "Shopping"};

        selectCategory = new JComboBox(categories);
        selectCategory.setBounds(80, 580, 120, 40);
        selectCategory.setForeground(Color.BLUE);

        addActions(expenseList);

        this.add(submit);
        this.add(selectCategory);
    }

    private void addActions(ExpenseList expenseList) {
        selectCategory.addActionListener(e -> System.out.println(selectCategory.getSelectedItem()));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expenses = expenseList.getExpensesFromCategory(selectCategory.getSelectedItem().toString());
                model.clear();
                loadList();

            }
        });
    }
}
