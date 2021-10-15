package model;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Expense {
    private double amount;
    private String description;
    private String category;
    private LocalDateTime date;

    public Expense(double amount, String description, String category, LocalDateTime date) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    //REQUIRES: newAmount be a positive number
    //MODIFIES: this
    //EFFECTS: updates the dollar amount of the expense
    public void setAmount(double newAmount) {
        amount = newAmount;
    }

    //MODIFIES: this
    //EFFECTS: updates the expense's description
    public void setDescription(String newDescription) {
        description = newDescription;
    }

    //MODIFIES: this
    //EFFECTS: updates the expense's category
    public void setCategory(String newCategory) {
        category = newCategory;
    }

    //MODIFIES: this
    //EFFECTS: updates the date of the expense
    public void setDate(LocalDateTime newDate) {
        date = newDate;
    }

    //EFFECTS: returns amount
    public Double getAmount() {
        return amount;
    }

    //EFFECTS: returns description
    public String getDescription() {
        return description;
    }

    //EFFECTS: returns category
    public String getCategory() {
        return category;
    }

    //EFFECTS: returns date
    public LocalDateTime getDate() {
        return date;
    }

    //EFFECTS: formats the date from a LocalDateTime object and returns a string in the format
    // 09:10 Oct 12, 2021
    public String dateToString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm MMM dd, yyyy");
        return date.format(format);
    }

    //EFFECTS: formats the information from an Expense object into a String
    @Override
    public String toString() {
        return String.format("%-10.2f %-20s %-15s %s", amount, description, category, dateToString());
    }
}
