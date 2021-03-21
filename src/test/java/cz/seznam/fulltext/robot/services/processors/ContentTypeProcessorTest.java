package cz.seznam.fulltext.robot.services.processors;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class ContentTypeProcessorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void processCountCorrect() {
        String[] params = new String[]{};
        ContentTypeProcessor p = new ContentTypeProcessor(params);

        String line = "asdwqdqw text/html 46456";
        p.process(line);
        line = "asdwqdqw text/pdf 456456";
        p.process(line);
        line = "asdwqdqw text/html 65464";
        p.process(line);

        TreeMap<String, Integer> expectedMap = new TreeMap<>();
        expectedMap.put("text/html", 2);
        expectedMap.put("text/pdf ", 1);

        assertEquals(expectedMap, p.getContentTypeToCount());
    }

    @Test
    void processWrongStructure() {
        String [] params = new String[]{};
        ContentTypeProcessor p = new ContentTypeProcessor(params);

        String line = "asdwqdqw";
        p.process(line);

        assertTrue(p.getContentTypeToCount().isEmpty());
    }

    @Test
    void writeOutput() {
        String[] params = new String[]{};
        ContentTypeProcessor p = new ContentTypeProcessor(params);

        String line = "asdwqdqw text/html 46456";
        p.process(line);

        p.writeOutput();
        assertEquals("text/html\t1", outContent.toString());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
