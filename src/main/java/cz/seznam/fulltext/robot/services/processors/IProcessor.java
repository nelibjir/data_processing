package cz.seznam.fulltext.robot.services.processors;

public interface IProcessor {
    void process(String[] args);
    void writeOutput();
}
