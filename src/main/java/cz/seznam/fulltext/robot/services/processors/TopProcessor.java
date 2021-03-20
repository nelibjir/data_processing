package cz.seznam.fulltext.robot.services.processors;

import cz.seznam.fulltext.robot.models.TopClickNode;

import java.util.TreeSet;

public class TopProcessor implements IProcessor {
    private TreeSet<TopClickNode> topClickNodeSet;

    public TopProcessor(String[] params){
        topClickNodeSet = new TreeSet<>();
    }

    //todo check args
    @Override
    public void process(String line) {
        String[] attributes = line.split("\\t"); //TODO better name?
        writeHighestTenClicks(Integer.parseInt(attributes[2]),attributes[0]);
    }

    @Override
    public void writeOutput() {
        for (TopClickNode element : topClickNodeSet) {
            System.out.println(element);
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
