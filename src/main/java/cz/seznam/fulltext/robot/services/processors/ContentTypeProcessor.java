package cz.seznam.fulltext.robot.services.processors;

import java.util.*;

public class ContentTypeProcessor implements IProcessor{
    private final static String OUTPUT_SEPARATOR = "\t";
    private final static String INPUT_SEPARATOR = "\\t";

    private HashMap<String, Integer> contentTypeToCount; // will be small in memory

    public ContentTypeProcessor(String[] params) {
        contentTypeToCount = new HashMap<>();
    }

    // TODO and check rules about 1 line if - these args are content -> make enum
    /**
     * Process the line and makes mark for the given content type
     * columns[1] is content_type column
     * @param line to be processed
     */
    @Override
    public void process(String line) {
        String[] columns = line.split(INPUT_SEPARATOR);
        if (contentTypeToCount.computeIfPresent(columns[1], (k, v) -> v + 1) == null)
            contentTypeToCount.put(columns[1], 1);  // should be O(1)
    }

    @Override
    public void writeOutput() {
        TreeMap<String, Integer> sortedMap = new TreeMap<>((o1, o2) -> o1.toLowerCase().compareTo(o2.toLowerCase()));
        sortedMap.putAll(contentTypeToCount); //contentTyp will be small

        for (Map.Entry<String, Integer> element : sortedMap.entrySet()) {
            System.out.println(element.getKey() + OUTPUT_SEPARATOR + element.getValue());
        }
    }
}
