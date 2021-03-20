package cz.seznam.fulltext.robot.services.processors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrepProcessor implements IProcessor{
    private static Pattern pattern;

    public GrepProcessor(String[] args){
        String regex = args[1];
        pattern = Pattern.compile(regex);
    }

    @Override
    public void process(String line) {
        Matcher matcher = pattern.matcher(line); // not thread safe
        if (matcher.find()){
            System.out.println(line);
        }
    }

    @Override
    public void writeOutput() {
    }
}
