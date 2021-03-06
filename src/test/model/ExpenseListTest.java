package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseListTest {
    private ExpenseList expenses;
    private Expense e1, e2, e3;

    @BeforeEach
    public void runBefore() {
        expenses = new ExpenseList();
        e1 = new Expense(100, "T&T Supermarket", "Groceries",
                LocalDateTime.of(2020, Month.OCTOBER, 12, 9,10));
        e2 = new Expense(59.5, "H&M", "Shopping", LocalDateTime.of(2020, Month.OCTOBER,
                12, 9,10));
        e3 = new Expense(20, "Cineplex", "Movies", LocalDateTime.of(2020, Month.JULY,
                12, 9,10));
    }

    @Test
    public void testAddExpense() {
        expenses.addExpense(e1, "add");
        expenses.addExpense(e2, "add");

        assertEquals(2, expenses.length());
        assertEquals(10, expenses.getExpensesFromMonth(10).get(0).getDate().getMonthValue());
        assertEquals(59.5, expenses.getExpensesFromMonth(10).get(1).getAmount());
    }

    @Test
    public void testGetAllExpenses() {
        expenses.addExpense(e1, "add");
        expenses.addExpense(e2, "add");
        expenses.addExpense(e3, "add");

        List<Expense> allExpenses  = expenses.getAllExpenses();

        assertEquals(3, allExpenses.size());
        assertEquals("T&T Supermarket", allExpenses.get(1).getDescription());
        assertEquals("Movies", allExpenses.get(0).getCategory());
        assertEquals(59.5, allExpenses.get(2).getAmount());
        assertEquals(LocalDateTime.of(2020, Month.JULY, 12, 9,10),
                allExpenses.get(0).getDate());
    }

    @Test
    public void testSortByDate() {
        e1 = new Expense(100, "T&T Supermarket", "Groceries",
                LocalDateTime.of(2020, Month.DECEMBER, 12, 9,10));
        e2 = new Expense(59.5, "H&M", "Shopping", LocalDateTime.of(2020, Month.JANUARY,
                12, 9,10));
        e3 = new Expense(20, "Cineplex", "Movies", LocalDateTime.of(2020, Month.JULY,
                12, 9,10));
        expenses.addExpense(e1, "add");
        expenses.addExpense(e2, "add");
        expenses.addExpense(e3, "add");

        expenses.sortByDate();
        List<Expense> expensesSortedByDate  = expenses.getAllExpenses();

        assertEquals(3, expensesSortedByDate.size());
        assertEquals(e2, expensesSortedByDate.get(0));
        assertEquals(e3, expensesSortedByDate.get(1));
        assertEquals(e1, expensesSortedByDate.get(2));
    }

    @Test
    public void testGetAllExpensesFromMonthDoesNotExist() {
        expenses.addExpense(e1, "add");
        expenses.addExpense(e2, "add");
        expenses.addExpense(e3, "add");

        List<Expense> allExpensesJan  = expenses.getExpensesFromMonth(1);
        assertEquals(0, allExpensesJan.size());
    }

    @Test
    public void testGetAllExpensesFromMonthExists() {
        expenses.addExpense(e1, "add");
        expenses.addExpense(e2, "add");
        expenses.addExpense(e3, "add");

        List<Expense> allExpensesOct  = expenses.getExpensesFromMonth(10);
        assertEquals(2, allExpensesOct.size());
        assertEquals(e1, allExpensesOct.get(0));
        assertEquals(e2, allExpensesOct.get(1));
    }

    @Test
    public void testGetAllExpensesFromCategoryDoesNotExist() {
        expenses.addExpense(e1, "add");
        expenses.addExpense(e2, "add");
        expenses.addExpense(e3, "add");

        List<Expense> allExpensesRestaurant = expenses.getExpensesFromCategory("Restaurant");
        assertEquals(0, allExpensesRestaurant.size());
    }

    @Test
    public void testGetAllExpensesFromCategoryExists() {
        Expense e4 = new Expense(23, "Cineplex", "Movies",
                LocalDateTime.of(2020, Month.JANUARY, 12, 23,10));
        expenses.addExpense(e1, "add");
        expenses.addExpense(e2, "add");
        expenses.addExpense(e3, "add");
        expenses.addExpense(e4, "add");

        List<Expense> allExpensesRestaurant = expenses.getExpensesFromCategory("Movies");
        assertEquals(2, allExpensesRestaurant.size());
        assertEquals(e3, allExpensesRestaurant.get(0));
        assertEquals(e4, allExpensesRestaurant.get(1));
    }

    @Test
    public void testRemoveExpenseExists() {
        expenses.addExpense(e1, "add");
        expenses.addExpense(e2, "add");
        expenses.addExpense(e3, "add");

        assertEquals(3, expenses.length());

        expenses.removeExpense(e1);

        assertEquals(2, expenses.length());
        assertEquals(e2, expenses.getAllExpenses().get(1));
    }


    @Test
    public void testRemoveExpenseDoesNotExist() {
        expenses.addExpense(e1, "add");
        expenses.addExpense(e2, "add");
        expenses.addExpense(e3, "add");

        assertEquals(3, expenses.length());

        expenses.removeExpense(new Expense(10, "Superstore", "Groceries",
                LocalDateTime.of(2020, Month.OCTOBER, 12, 9,10)));
        assertEquals(3, expenses.length());

        assertEquals(e2, expenses.getAllExpenses().get(2));
    }

    @Test
    public void testRemoveExpenseIndex() {
        expenses.addExpense(e1, "add");
        expenses.addExpense(e2, "add");
        expenses.addExpense(e3, "add");

        assertEquals(3, expenses.length());
        assertEquals(e2, expenses.removeExpense(2));
        assertEquals(2, expenses.length());

        assertEquals(e1, expenses.getAllExpenses().get(1));
        assertEquals(e3, expenses.getAllExpenses().get(0));
    }

    @Test
    public void testLength() {
        expenses.addExpense(e1, "add");
        assertEquals(1, expenses.length());

        for(int i = 1; i < 15; i++) {
            expenses.addExpense(e1, "add");
        }

        assertEquals(15, expenses.length());
    }
}