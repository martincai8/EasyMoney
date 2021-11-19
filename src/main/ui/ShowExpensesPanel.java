package ui;

import model.Expense;
import model.ExpenseList;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Represents a ShowExpensesPanel
public class ShowExpensesPanel extends JPanel {
    protected JList<Expense> jlist;
    protected DefaultListModel<Expense> model;
    protected List<Expense> expenses;
    protected JScrollPane scroll;
    protected JButton home;

    // Constructs a ShowExpensePanel
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
        this.home.setBounds(160, 650, 100, 50);
        this.home.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.home.addActionListener(e -> cards.show(cardLayoutPanel, "home page"));

        loadList();

        this.jlist.setModel(model);
        this.jlist.setSelectedIndex(0);
        this.scroll.setBounds(0, 10, (int) (width * 0.95), (int) (height * 0.7));
        this.jlist.setFont(new Font("Monospaced", Font.PLAIN, 10));

        this.add(scroll);
        this.add(home);

    }

    // MODIFIES: model
    // EFFECTS: populates the model with expenses
    public void loadList() {
        for (Expense e : expenses) {
            model.addElement(e);
        }
    }

    // MODIFIES: model
    // EFFECTS: resets and populates the model with new expenses (after filtering)
    public void refresh(List<Expense> newExpenses) {
        model.clear();
        for (Expense e : newExpenses) {
            model.addElement(e);
        }
    }
}
