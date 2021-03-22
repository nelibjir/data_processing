package cz.seznam.fulltext.robot.services.processors;

import cz.seznam.fulltext.robot.models.TopClickNode;

import java.util.Iterator;
import java.util.TreeSet;

public class TopProcessor implements IProcessor {
    private final static String INPUT_SEPARATOR = "\\t";
    private final static int NUMBER_OF_COLUMNS = 3;
    private final static int NUMBER_OF_CLICKS_COLUMN_INDEX = 2;
    private final static int NUMBER_OF_TOP_URL = 10;
    private final static int URL_COLUMN_INDEX = 0;

    private TreeSet<TopClickNode> topClickNodeSet;

    public TopProcessor(String[] params){
        topClickNodeSet = new TreeSet<>();
    }

    @Override
    public void process(String line) {
        if (line == null || line.isEmpty())
            return;

        String[] columns = line.split(INPUT_SEPARATOR);
        if (columns.length != NUMBER_OF_COLUMNS)
            return;

        int numOfClicks = getNumberOfClicks(columns[NUMBER_OF_CLICKS_COLUMN_INDEX]);
        if (numOfClicks < 0)
            return;

        writeHighestTenClicks(numOfClicks,columns[URL_COLUMN_INDEX]);
    }

    @Override
    public void writeOutput() {
        Iterator itr = topClickNodeSet.descendingIterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    private void addNewNode(TopClickNode newValue){
        if (topClickNodeSet.size() < NUMBER_OF_TOP_URL) {
            topClickNodeSet.add(newValue);
            return;
        }

        if (topClickNodeSet.first().getNumberOfClicks() < newValue.getNumberOfClicks()) {
            topClickNodeSet.add(newValue);  //O(logN) worse for now
            topClickNodeSet.pollFirst();
        }
    }

    private void writeHighestTenClicks(int numOfClicks, String url){
        addNewNode(new TopClickNode(numOfClicks, url));
    }

    private int getNumberOfClicks (String numberOfClicks){
        int numOfClicks;
        try {
            numOfClicks = Integer.parseInt(numberOfClicks);
        } catch (NumberFormatException ex){
            return -1;
        }

        return numOfClicks;
    }
}
