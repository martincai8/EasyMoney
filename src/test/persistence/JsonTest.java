package persistence;

import model.Expense;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

// The class is modelled from the JsonTest class from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkExpense(Double amount, String description, String category, LocalDateTime date, Expense e) {
        assertEquals(amount, e.getAmount());
        assertEquals(description, e.getDescription());
        assertEquals(category, e.getCategory());
        assertEquals(date, e.getDate());
    }
}
