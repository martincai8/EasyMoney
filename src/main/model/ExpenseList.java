package model;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
    //EFFECTS: returns all expenses from a given integer month value
    public List<Expense> getExpensesFromMonth(int month) {
        List<Expense> expensesMonth = new ArrayList<>();
        for (Expense e : expenses) {
            if (e.getDate().getMonthValue() == month) {
                expensesMonth.add(e);
            }
        }
        return expensesMonth;
    }

    //REQUIRES: expenses isn't empty
    //EFFECTS: returns all expenses from a given category
    public List<Expense> getExpensesFromCategory(String category) {
        List<Expense> expensesCategory = new ArrayList<>();
        for (Expense e : expenses) {
            if (e.getCategory().equals(category)) {
                expensesCategory.add(e);
            }
        }
        return expensesCategory;
    }

    //REQUIRES: expenses is not empty
    //MODIFIES: this
    //EFFECTS: removes the expense with the given amount, category, and date from expenses
    public void removeExpense(double amount, String description, String category, LocalDateTime date) {
        Expense target = new Expense(amount, description, category, date);
        for (Expense e : expenses) {
            if (checkEquals(e, target)) {
                expenses.remove(e);
                break;
            }
        }
    }

    public boolean checkEquals(Expense e1, Expense e2) {
        return (e1.getAmount().equals(e2.getAmount()))
                && e1.getDescription().equals(e2.getDescription())
                && e1.getCategory().equals(e2.getCategory())
                && e1.getDate().equals(e2.getDate());

    }

    //EFFECTS: returns the number of expenses in the list
    public Integer length() {
        return expenses.size();
    }
}
