package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class ExpenseList {
    private List<Expense> expenses;

    // EFFECTS: expenses is empty
    public ExpenseList() {
        expenses = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds an expense to the list of expenses
    public void addExpense(Expense e) {
        expenses.add(e);
    }

    //REQUIRES: expenses isn't empty
    //EFFECTS: returns all expenses
    public List<Expense> getAllExpenses() {
        return expenses;
    }

    //REQUIRES: expenses isn't empty
    //EFFECTS: returns all expenses from a specified integer month value
    public List<Expense> getExpensesFromMonth(int month) {
        List<Expense> expenseMonth = new ArrayList<>();
        for (Expense e : expenses) {
            if (e.getDate().getMonthValue() == month) {
                expenseMonth.add(e);
            }
        }
        return expenseMonth;
    }

    //EFFECTS: returns the number of expenses in the list
    public Integer length() {
        return expenses.size();
    }
}
