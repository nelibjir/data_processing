package cz.seznam.fulltext.robot.services.processors;

import java.util.*;

public class ContentTypeProcessor implements IProcessor{
    private HashMap<String, Integer> contentTypeToCount; // will be small in memory

    public ContentTypeProcessor(String[] params) {
        contentTypeToCount = new HashMap<>();
    }

    //https://stackoverflow.com/questions/4157972/how-to-update-a-value-given-a-key-in-a-hashmap
    // concurrent solution there
    // TODO literals remove and chack rules about 1 line if - these args are content -> make enum
    @Override
    public void process(String line) {
        String[] attributes = line.split("\\t");
        if (contentTypeToCount.computeIfPresent(attributes[1], (k, v) -> v + 1) == null)
            contentTypeToCount.put(attributes[1], 1);  // should be O(1)
    }

    @Override
    public void writeOutput() {
        TreeMap<String, Integer> sortedMap = new TreeMap<>((o1, o2) -> o1.toLowerCase().compareTo(o2.toLowerCase()));
        sortedMap.putAll(contentTypeToCount); //contentTyp will be small

        for (Map.Entry<String, Integer> element : sortedMap.entrySet()) {
            System.out.println(element.getKey() + "\t" + element.getValue());
        }
    }
}
