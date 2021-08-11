package main.persistence;

import main.model.Player;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//This class is a reader tool using JSON to read data stored previously
//CITATION: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {

    public static final String SOURCE = "./data/userData.json";

    private String source;

    //EFFECTS: set this.source to SOURCE
    public JsonReader() {
        source = SOURCE;
    }

    //EFFECTS: set this.source to source
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: read file from SOURCE and return the parsed player
    public Player read() throws IOException {
        String data = readFile(source);
        JSONObject jsonObject = new JSONObject(data);
        return parsePlayer(jsonObject);
    }

    //EFFECTS: parse a player from given JOSNObject
    private Player parsePlayer(JSONObject jsonObject) {
        String name = jsonObject.getString(Player.JSON_NAME);
        int score = jsonObject.getInt(Player.JSON_SCORE);
        int RGB = jsonObject.getInt(Player.JSON_COLOR);

        return new Player(name, score, new Color(RGB));
    }

    //EFFECTS: return String from given source
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }
}
