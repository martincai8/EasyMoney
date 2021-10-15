package ui;

import model.Expense;
import model.ExpenseList;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

//EasyMoney application
public class EasyMoneyApp {
    private ExpenseList expenseList;
    private Scanner input;

    //EFFECTS: runs the EasyMoney application
    public EasyMoneyApp() {
        runEasyMoney();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runEasyMoney() {
        boolean keepGoing = true;
        String option;

        init();

        displayWelcome();

        while (keepGoing) {
            displayMenu();
            System.out.print("Command: ");
            option = input.next().toLowerCase();

            if (option.equals("q")) {
                System.out.println("Thank you for using EasyMoney, have a great day!");
                keepGoing = false;
            } else {
                processOption(option);
            }
            System.out.print("\n");

        }
    }

    //EFFECTS: displays the welcome title
    private void displayWelcome() {
        System.out.println("Welcome to EasyMoney!");
        System.out.println("***********************");
    }

    //EFFECTS: displays the menu options for the user to choose from
    private void displayMenu() {
        System.out.println("Please pick from one of the following options:");
        System.out.println("a -> view all expenses");
        System.out.println("b -> view all expenses from a certain category");
        System.out.println("c -> view all expenses from a certain month");
        System.out.println("d -> add a new expense");
        System.out.println("e -> delete an existing expense");
        System.out.println("q -> quit");
    }

    //MODIFIES: this
    //EFFECTS: processes the user's selected option
    private void processOption(String opt) {
        String option = opt;

        if (option.equals("a")) {
            viewAllExpenses();
        } else if (option.equals("b")) {
            viewAllExpensesFromCategory();
        } else if (option.equals("c")) {
            viewAllExpensesFromMonth();
        } else if (option.equals("d")) {
            addNewExpense();
        } else if (option.equals("e")) {
            deleteExpense();
        } else {
            System.out.println("Please try again: ");
            option = input.next().toLowerCase();
            processOption(option);
        }
    }

    //EFFECTS: displays all the user's logged expenses
    private void viewAllExpenses() {
        List<Expense> expenses = expenseList.getAllExpenses();
        System.out.println("Here are all your expenses:");
        displayExpenses(expenses);
    }

    //EFFECTS: displays all the user's logged expenses from a specific category
    private void viewAllExpensesFromCategory() {
        String category;
        List<Expense> expenses;

        System.out.print("Enter a category: ");
        category = input.next();
        expenses = expenseList.getExpensesFromCategory(category);
        System.out.println("Here are your expenses from " + category);
        displayExpenses(expenses);
    }

    //EFFECTS: displays all the user's logged expenses from a specific month
    private void viewAllExpensesFromMonth() {
        int month;
        List<Expense> expenses;

        System.out.print("Enter a month (1-12): ");
        month = input.nextInt();
        while (month < 1 || month > 12) {
            System.out.println("Please enter a valid month value!");
            System.out.print("Enter a month (1-12): ");
            month = input.nextInt();
        }
        expenses = expenseList.getExpensesFromMonth(month);
        System.out.println("Here are your expenses from " + month);
        displayExpenses(expenses);
    }

    //MODIFIES: this
    //EFFECTS: adds a new expense to expenseList
    private void addNewExpense() {
        double amount;
        String description;
        String category;
        String dateStr;
        LocalDateTime date;


        System.out.print("Enter the dollar amount of the expense: $");
        amount = input.nextDouble();

        System.out.print("Enter a description of the expense: ");
        description = input.next();

        System.out.print("Enter the category that this expense belongs to: ");
        category = input.next();

        System.out.print("Enter the date and time of this expense (e.g., \"09:30 Oct. 12, 2021\"): ");
        dateStr = input.next();
        date = stringToDate(dateStr);

        Expense newExpense = new Expense(amount,description,category,date);

        expenseList.addExpense(newExpense);

        System.out.println("Successfully added!");
    }

    //MODIFIES: this
    //EFFECTS: deletes an existing expense to expenseList
    public void deleteExpense() {
        double amount;
        String description;
        String category;

        LocalDateTime date;

        System.out.print("Enter the dollar amount of the expense: $");
        amount = input.nextDouble();

        System.out.print("Enter a short description of the expense (e.g., Superstore): ");
        description = input.nextLine();

        System.out.println("Enter the category that this expense belongs to (e.g., Groceries): ");
        category = input.next();

        System.out.println("Enter the date and time of this expense (e.g., \"09:30 Oct 12, 2021\"): ");
        date = stringToDate(input.nextLine());

        Expense target = new Expense(amount, description, category, date);

        expenseList.removeExpense(target);

        System.out.println("Successfully deleted!");
    }

    //EFFECTS: displays a given list of expenses
    private void displayExpenses(List<Expense> expenses) {
        if (expenses.size() == 0) {
            System.out.println("You don't have any expenses, add one!");
        } else {
            System.out.printf("\n%-10s %-25s %-20s %s \n", "Amount", "Description", "Category", "Date");
            for (Expense e : expenses) {
                System.out.println(e.toString());
            }
        }
    }

    //EFFECTS: returns a LocalDateTime object parsed from a string
    public LocalDateTime stringToDate(String dateStr) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm MMM dd, yyyy");
        return LocalDateTime.parse(dateStr, format);
    }

    // MODIFIES: this
    // EFFECTS: initializes expenseList and adds sample expenses
    private void init() {
        Expense e1 = new Expense(100, "T&T Supermarket", "Groceries",
                LocalDateTime.of(2021, Month.JANUARY, 9, 20,15));
        Expense e2 = new Expense(59.5, "H&M", "Shopping",
                LocalDateTime.of(2021, Month.FEBRUARY, 14, 9,10));
        Expense e3 = new Expense(20, "Cineplex", "Movies",
                LocalDateTime.of(2021, Month.FEBRUARY, 14, 14,1));
        Expense e4 = new Expense(23, "Cineplex", "Movies",
                LocalDateTime.of(2021, Month.OCTOBER, 12, 23,10));

        expenseList = new ExpenseList();
        expenseList.addExpense(e1);
        expenseList.addExpense(e2);
        expenseList.addExpense(e3);
        expenseList.addExpense(e4);

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }
}
