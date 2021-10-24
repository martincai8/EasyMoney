package persistence;

import model.ExpenseList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes expenses to JSON data stored in a file
// The class is modelled from the JsonWriter class from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs a writer to write data to a destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens the writer and throws a FileNotFoundException is file cannot be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of workroom to file
    public void write(ExpenseList el) {
        JSONObject json = el.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
