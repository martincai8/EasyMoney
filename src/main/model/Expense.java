package model;

import java.time.*;

public class Expense {
    private int amount;
    private String category;
    private LocalDateTime date;

    public Expense(int amount, String category, LocalDateTime date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    //MODIFIES: this
    //EFFECTS: updates the dollar amount of the expense
    public void setAmount(int newAmount) {
        amount = newAmount;
    }

    //MODIFIES: this
    //EFFECTS: updates the category of the expense
    public void setCategory(String newCategory) {
        category = newCategory;
    }

    //MODIFIES: this
    //EFFECTS: updates the date of the expense
    public void setDate(LocalDateTime newDate) {
        date = newDate;
    }

    //EFFECTS: returns amount
    public Integer getAmount() {
        return amount;
    }

    //EFFECTS: returns category
    public String getCategory() {
        return category;
    }

    //EFFECTS: returns date
    public LocalDateTime getDate() {
        return date;
    }


}
