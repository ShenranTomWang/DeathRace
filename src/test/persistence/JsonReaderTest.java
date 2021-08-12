package test.persistence;

import main.model.Player;
import main.persistence.JsonReader;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//This class is the test class for JsonReader
public class JsonReaderTest {

    public JsonReader reader;

    @Test
    public void testReadUser() {
        reader = new JsonReader("./data/readerTest.json");
        try {
            ArrayList<Player> playerList = reader.read();
            assertEquals(1, playerList.size());
            assertEquals(playerList.get(0).getName(), "p1");
            assertEquals(playerList.get(0).getScore(), 0);
            assertEquals(playerList.get(0).getCar().getColor().getRGB(), Color.red.getRGB());
        } catch (IOException exception) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testReadFileNotFound() {
        reader = new JsonReader("./data/noSuchFile.json");
        try {
            ArrayList<Player> playerList = reader.read();
            fail("Exception should be thrown");
        } catch (IOException e) {
            //success
        }
    }
}
