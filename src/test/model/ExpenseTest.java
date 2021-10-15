package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.*;
import static org.junit.jupiter.api.Assertions.*;
public class ExpenseTest {
    private Expense expense, expense2;
    private LocalDateTime date;

    @BeforeEach
    public void runBefore() {
        date = LocalDateTime.of(2020,Month.OCTOBER, 12, 9,10);
        expense = new Expense(100, "T&T Supermarket", "Groceries", date);
        expense2 = new Expense(25, "Cineplex", "Movies", date);
    }

    @Test
    public void testConstructor() {
        assertEquals(100, expense.getAmount());
        assertEquals("T&T Supermarket", expense.getDescription());
        assertEquals("Groceries", expense.getCategory());
        assertEquals(date, expense.getDate());
    }

    @Test
    public void testSetAmount() {
        expense.setAmount(99);
        assertEquals(99, expense.getAmount());
    }

    @Test
    public void testSetDescription() {
        expense.setDescription("Superstore");
        assertEquals("Superstore", expense.getDescription());
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

    @Test
    public void testDateToString() {
        assertEquals("09:10 Oct 12, 2020", expense.dateToString());
    }

    @Test
    public void testToString() {
        assertEquals("100.00     T&T Supermarket      Groceries       09:10 Oct 12, 2020",
                expense.toString());
        assertEquals("25.00      Cineplex             Movies          09:10 Oct 12, 2020",
                expense2.toString());
    }
}
