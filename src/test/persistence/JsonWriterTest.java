package test.persistence;

import main.model.Player;
import main.persistence.JsonReader;
import main.persistence.JsonWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    public Player playerWrite;
    public Player playerRead;
    public JsonWriter writer;
    public JsonReader reader;

    @BeforeEach
    public void setUp() {
        playerWrite = new Player("p1", 0, 0, 0, Color.red);
    }

    @Test
    public void testWrite() {
        try {
            writer = new JsonWriter("./data/writerTest.json");
            reader = new JsonReader("./data/writerTest.json");
            writer.open();
            writer.write(playerWrite);
            writer.close();
            playerRead = reader.read();
            assertEquals(playerWrite, playerRead);
        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testWriteFileNotFound() {
        try {
            writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("Exception should be thrown");
        } catch (FileNotFoundException e) {
            //success
        }
    }
}
