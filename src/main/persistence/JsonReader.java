package persistence;

import model.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
import org.json.*;

// Represents a reader that reads expenses from JSON data stored in a file
// The class is modelled from the JsonReader class from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads expenses from file and returns it
    public ExpenseList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExpenseList(jsonObject);
    }

    //EFFECTS: reads info from source file and returns it as a string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses expenses from JSON object and returns it
    private ExpenseList parseExpenseList(JSONObject jsonObject) {
        ExpenseList el = new ExpenseList();
        addExpenses(el, jsonObject);
        return el;
    }

    //MODIFIES: el
    //EFFECTS: parses expenses from JSON object and adds them to the list of expenses
    private void addExpenses(ExpenseList el, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(el, nextExpense);
        }
    }

    //MODIFIES: el
    //EFFECTS: parses expense from JSON object and adds them to the list of expenses
    private void addExpense(ExpenseList el, JSONObject jsonObject) {
        Double amount = jsonObject.getDouble("amount");
        String description = jsonObject.getString("description");
        String category = jsonObject.getString("category");
        String dateStr = jsonObject.getString("date");
        LocalDateTime date = stringToDate(dateStr);
        Expense e = new Expense(amount, description, category, date);
        el.addExpense(e);
    }

    //EFFECTS: returns a LocalDateTime object parsed from a string
    public LocalDateTime stringToDate(String dateStr) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm MMM dd, yyyy");
        return LocalDateTime.parse(dateStr, format);
    }
}
