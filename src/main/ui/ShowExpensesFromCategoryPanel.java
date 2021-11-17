package ui;

import model.ExpenseList;

import javax.swing.*;
import java.awt.*;

public class ShowExpensesFromCategoryPanel extends ShowExpensesPanel {
    private JButton submit;
    public ShowExpensesFromCategoryPanel(int width, int height, ExpenseList expenseList,
                                         CardLayout cards, JPanel cardLayoutPanel) {
        super(width, height, expenseList, cards, cardLayoutPanel);

        submit = new JButton("submit");
        submit.setBounds(220, 580, 80, 40);
        submit.addActionListener(e -> cards.show(cardLayoutPanel, "start up page"));
        this.add(submit);
    }
}
