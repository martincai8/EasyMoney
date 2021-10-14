package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.*;
import static org.junit.jupiter.api.Assertions.*;
public class ExpenseTest {
    private Expense expense;
    private LocalDateTime date;

    @BeforeEach
    public void runBefore() {
        date = LocalDateTime.of(2020,Month.OCTOBER, 12, 9,10);
        expense = new Expense(100, "Groceries", date);
    }

    @Test
    public void testConstructor() {
        assertEquals(100, expense.getAmount());
        assertEquals("Groceries", expense.getCategory());
        assertEquals(date, expense.getDate());
    }

    @Test
    public void testSetAmount() {
        expense.setAmount(99);

        assertEquals(99, expense.getAmount());
    }

    @Test
    public void testSetCategory() {
        expense.setCategory("Movies");

        assertEquals("Movies", expense.getCategory());
    }

    @Test
    public void setDate() {
        expense.setDate(LocalDateTime.of(2019,Month.JULY, 1, 23,12));
        assertEquals(2019, expense.getDate().getYear());
        assertEquals(7, expense.getDate().getMonthValue());
        assertEquals(1, expense.getDate().getDayOfMonth());
        assertEquals(23, expense.getDate().getHour());
        assertEquals(12, expense.getDate().getMinute());
    }
}
