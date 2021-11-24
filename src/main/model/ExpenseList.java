package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    public void addExpense(Expense e, String option) {
        expenses.add(e);
        if (option.equals("load")) {
            EventLog.getInstance().logEvent(new Event("Loaded expense from " + e.getDescription()
                    +
                    " to list."));
        } else if (option.equals("add")) {
            EventLog.getInstance().logEvent(new Event("Added expense from " + e.getDescription()
                    +
                    " to list."));
        }
    }

    //REQUIRES: expenses isn't empty
    //EFFECTS: returns all expenses
    public List<Expense> getAllExpenses() {
        this.sortByDate();
        return expenses;
    }

    //REQUIRES: expenses parameter isn't empty
    //EFFECTS: returns the passed in list of expenses sorted by date
    // this class references code from https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
    public List<Expense> sortByDate() {
        expenses.sort(Comparator.comparing(Expense::getDate));
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
        expenses.remove(target);
        EventLog.getInstance().logEvent(new Event("Removed expense from " + target.getDescription()
                +
                " from list."));
        return target;
    }

    //REQUIRES: expenses is not empty
    //MODIFIES: this
    //EFFECTS: removes the expense at a specific position in expenses and returns it
    public Expense removeExpense(int position) {
        Expense target = expenses.get(position - 1);
        expenses.remove(target);
        EventLog.getInstance().logEvent(new Event("Removed expense from " + target.getDescription()
                +
                " from list."));
        return target;
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
