package cz.seznam.fulltext.robot.services.processors;

import cz.seznam.fulltext.robot.models.TopClickNode;

import java.util.Iterator;
import java.util.TreeSet;

public class TopProcessor implements IProcessor {
    private final static String INPUT_SEPARATOR = "\\t";

    private TreeSet<TopClickNode> topClickNodeSet;

    public TopProcessor(String[] params){
        topClickNodeSet = new TreeSet<>();
    }

    //todo check args
    @Override
    public void process(String line) {
        String[] columns = line.split(INPUT_SEPARATOR);
        writeHighestTenClicks(Integer.parseInt(columns[2]),columns[0]);
    }

    @Override
    public void writeOutput() {
        Iterator itr = topClickNodeSet.descendingIterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    private void addNewNode(TopClickNode newValue){
        if (topClickNodeSet.size() < 10) {
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
}
