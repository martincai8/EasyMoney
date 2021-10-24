package persistence;

import model.Expense;
import model.ExpenseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// The class is modelled from the JsonReaderTest class from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest extends JsonTest{
    private ExpenseList expenses;

    @BeforeEach
    public void runBefore() {
        expenses = new ExpenseList();
    }


    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            expenses = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testReaderEmptyExpenseList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExpenseList.json");
        try {
            expenses = reader.read();
            assertEquals(0, expenses.getAllExpenses().size());
        } catch (IOException e) {
            fail("couldn't read file");
        }
    }

    @Test
    public void testReaderNonEmptyExpenseList() {
        JsonReader reader = new JsonReader("./data/testReaderNonEmptyExpenseList.json");
        try {
            expenses = reader.read();
            List<Expense> el = expenses.getAllExpenses();
            assertEquals(2, el.size());
            checkExpense(100.0, "Uniqlo", "Shopping",
                    reader.stringToDate("19:10 Jan 14, 2021"), el.get(1));
        } catch (IOException e) {
            fail("couldn't read file");
        }
    }

}
