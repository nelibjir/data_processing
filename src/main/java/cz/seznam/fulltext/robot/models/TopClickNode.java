package cz.seznam.fulltext.robot.models;

import java.util.Objects;

public class TopClickNode implements Comparable<TopClickNode>{
    private final static String OUTPUT_SEPARATOR = "\t";

    private final int numberOfClicks;
    private final String url;

    public TopClickNode(int numberOfClicks, String url) {
        this.numberOfClicks = numberOfClicks;
        this.url = url;
    }

    public int getNumberOfClicks() {
        return numberOfClicks;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int compareTo(TopClickNode o) {
        return o.numberOfClicks < this.numberOfClicks ? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopClickNode that = (TopClickNode) o;
        return getUrl().equals(that.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl());
    }

    @Override
    public String toString() {
        return url + OUTPUT_SEPARATOR + numberOfClicks;
    }
}
