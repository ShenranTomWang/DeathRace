package test.persistence;

import main.model.Player;
import main.persistence.JsonReader;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    public JsonReader reader;

    @Test
    public void testReadUser() {
        reader = new JsonReader("./data/readerTest.json");
        try {
            Player p = reader.read();
            assertEquals(p.getName(), "p1");
            assertEquals(p.getScore(), 0);
            assertEquals(p.getCar().getColor().getRGB(), Color.red.getRGB());
        } catch (IOException exception) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testReadFileNotFound() {
        reader = new JsonReader("./data/noSuchFile.json");
        try {
            Player p = reader.read();
            fail("Exception should be thrown");
        } catch (IOException e) {
            //success
        }
    }
}
