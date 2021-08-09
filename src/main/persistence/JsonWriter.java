package main.persistence;

import main.model.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//This class represents a tool to write to a .json file
//CITATION: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriter {

    public static final int TAB = 4;
    public static final String DIR = "./data/userData.json";

    private PrintWriter writer;

    //MODIFIES: this
    //EFFECTS: opens writer, throw FileNotFoundException if file at DIR cannot be used for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(DIR));
    }

    //MODIFIES: this
    //EFFECTS: write player's data to json file
    public void write(Player player) throws JSONException {
        JSONObject json = player.toJson();
        writer.print(json.toString(TAB));
    }

    //MODIFIES: this
    //EFFECTS: close the writer
    public void close() {
        writer.close();
    }
}
