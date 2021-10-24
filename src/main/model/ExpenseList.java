package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

//Represents a list of Expenses
public class ExpenseList implements Writable {
    private List<Expense> expenses;

    // EFFECTS: constructs ExpenseList with an empty list of expenses
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
    //EFFECTS: returns all expenses from a given month
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
    //EFFECTS: removes the expense with the matching amount, description, category, and date from expenses
    public Expense removeExpense(Expense target) {
//        for (Expense e : expenses) {
//            if (checkEquals(e, target)) {
//                expenses.remove(e);
//                break;
//            }
//        }
        expenses.remove(target);
        return target;
    }

    //REQUIRES: expenses is not empty
    //MODIFIES: this
    //EFFECTS: removes the expense at a specific position in expenses and returns it
    public Expense removeExpense(int position) {
        Expense target = expenses.get(position - 1);
        expenses.remove(target);
        return target;
    }

    //EFFECTS: returns true if two Expense objects are identical (all field values are the same)
    public boolean checkEquals(Expense e1, Expense e2) {
        return (e1.getAmount().equals(e2.getAmount()))
                && e1.getDescription().equals(e2.getDescription())
                && e1.getCategory().equals(e2.getCategory())
                && e1.getDate().equals(e2.getDate());
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("expenses", expensesToJson());
        return json;
    }

    //EFFECTS: returns the expenses in this list as a JSON array
    public JSONArray expensesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Expense e : expenses) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }

    //EFFECTS: returns the number of expenses in the list
    public Integer length() {
        return expenses.size();
    }
}
