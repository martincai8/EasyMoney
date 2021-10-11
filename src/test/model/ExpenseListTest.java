package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseListTest {
    private ExpenseList expenses;

    @BeforeEach
    public void runBefore() {
        expenses = new ExpenseList();
    }

    @Test
    public void testAddExpense() {
        Expense e = new Expense(100, "Groceries", LocalDateTime.now());
        expenses.addExpense(e);

        assertEquals(1, expenses.length());
        assertEquals(10, expenses.getExpensesFromMonth(10).get(0).getDate().getMonthValue());
        assertEquals(100, expenses.getExpensesFromMonth(10).get(0).getAmount());
    }
}