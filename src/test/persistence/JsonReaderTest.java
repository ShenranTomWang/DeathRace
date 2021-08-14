package test.persistence;

import main.model.Player;
import main.persistence.JsonReader;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//This class is the test class for JsonReader
public class JsonReaderTest {

    public JsonReader reader;

    @Test
    public void testReadUser() {
        reader = new JsonReader("./data/readerTest.json");
        try {
            HashSet<Player> playerList = reader.read();
            assertEquals(1, playerList.size());
            Player[] playerArray = (Player[]) playerList.toArray();
            assertEquals(playerArray[0].getName(), "p1");
            assertEquals(playerArray[0].getScore(), 0);
            assertEquals(playerArray[0].getCar().getColor().getRGB(), Color.red.getRGB());
        } catch (IOException exception) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testReadFileNotFound() {
        reader = new JsonReader("./data/noSuchFile.json");
        try {
            HashSet<Player> playerList = reader.read();
            fail("Exception should be thrown");
        } catch (IOException e) {
            //success
        }
    }
}
