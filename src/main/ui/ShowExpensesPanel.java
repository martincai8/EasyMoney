package ui;

import model.Expense;
import model.ExpenseList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShowExpensesPanel extends JPanel {
    protected JList<Expense> jlist;
    protected DefaultListModel<Expense> model;
    protected List<Expense> expenses;
    protected JScrollPane scroll;
    protected JButton home;

    public ShowExpensesPanel(int width, int height, ExpenseList expenseList,
                             CardLayout cards, JPanel cardLayoutPanel) {
        this.setBackground(Color.WHITE);
        this.setBounds((int) (width * 0.025), (int) (height * 0.05), (int) (width * 0.95), (int) (height * 0.9));
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setLayout(null);

        this.jlist = new JList<>();
        this.model = new DefaultListModel<>();
        this.expenses = expenseList.getAllExpenses();
        this.scroll = new JScrollPane(jlist, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.home = new JButton("home");

        home.setBounds(160, 650, 100, 50);
        home.addActionListener(e -> cards.show(cardLayoutPanel, "home page"));

        loadList();

        this.jlist.setModel(model);
        this.jlist.setSelectedIndex(0);
        this.scroll.setBounds(0, 10, (int) (width * 0.95), (int) (height * 0.7));

        this.add(scroll);
        this.add(home);

    }

    public void loadList() {
        for (Expense e : expenses) {
            model.addElement(e);
        }
    }

    public void refresh(List<Expense> newExpenses) {
        model.clear();
        for (Expense e : newExpenses) {
            model.addElement(e);
        }
    }
}
