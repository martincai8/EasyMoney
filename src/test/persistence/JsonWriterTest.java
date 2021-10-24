package persistence;

import model.Expense;
import model.ExpenseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// The class is modelled from the JsonWriterTest class from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest extends JsonTest {
    private ExpenseList expenses;

    @BeforeEach
    public void runBefore() {
        expenses = new ExpenseList();
    }

    @Test
    public void testWriterFileNotFound() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("FileNotFoundException was expected");
        } catch (FileNotFoundException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyExpenseList() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExpenseList.json");
            writer.open();
            writer.write(expenses);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExpenseList.json");
            expenses = reader.read();
            List<Expense> el = expenses.getAllExpenses();
            assertEquals(0, el.size());
        } catch (IOException e) {
            fail("an exception should have been thrown");
        }
    }

    @Test
    public void testWriterNonEmptyExpenseList() {
        try {
            expenses.addExpense(new Expense(100, "T&T Supermarket", "Groceries",
                    LocalDateTime.of(2020, Month.OCTOBER, 12, 9,10)));
            expenses.addExpense(new Expense(59.5, "H&M", "Shopping",
                    LocalDateTime.of(2020, Month.OCTOBER, 12, 9,10)));
            expenses.addExpense(new Expense(20, "Cineplex", "Movies",
                    LocalDateTime.of(2020, Month.JULY, 12, 9,10)));

            JsonWriter writer = new JsonWriter("./data/testWriterNonEmptyExpenseList.json");
            writer.open();
            writer.write(expenses);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNonEmptyExpenseList.json");
            expenses = reader.read();
            List<Expense> el = expenses.getAllExpenses();
            assertEquals(3, el.size());
            assertEquals("T&T Supermarket", el.get(0).getDescription());
            assertEquals(59.5, el.get(1).getAmount());
            assertEquals("Movies", el.get(2).getCategory());
            assertEquals(LocalDateTime.of(2020, Month.JULY, 12, 9,10),
                    el.get(2).getDate());
        } catch (IOException e) {
            fail("exception should not have been thrown");
        }
    }

}
