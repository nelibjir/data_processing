package cz.seznam.fulltext.robot.utils.readers;

import cz.seznam.fulltext.robot.services.processors.ContentTypeProcessor;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class DataReaderTest {

    @Test
    void readAndProcess() throws IOException {
        String data = "url\ttext\t156\r\n";
        String[] args = new String[]{};
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        ContentTypeProcessor p = new ContentTypeProcessor(args);
        DataReader.readAndProcess(new InputStreamReader(System.in), p);

        TreeMap<String, Integer> expectMap = new TreeMap<>();
        expectMap.put("text", 1);
        assertEquals(expectMap, p.getContentTypeToCount());
    }
}