package cz.seznam.fulltext.robot.validators;

import cz.seznam.fulltext.robot.enums.ProcessorEnum;

import java.util.Locale;

public class CommandLineValidator {
    private static final int MAX_NUMBER_ARG = 2;
    private String[] args;

    public CommandLineValidator(String[] args){
        this.args = args;
    }

    public void checkArgs() {
        if (args == null || args.length == 0)
            throw new IllegalArgumentException("The application was called with no arguments!");

        if (args.length > MAX_NUMBER_ARG)
            throw new IllegalArgumentException("The application was called with to many arguments!" + args.length);

        String normalized_arg  = args[0].toUpperCase(Locale.ROOT);
        ProcessorEnum processor = getSupportedClass(normalized_arg);
        if (!hasCorrectNumberOfParameter(processor, args.length -1))
            throw new IllegalArgumentException("Processor "+ processor.name() + "was called with wrong number of arguments! " + args.length);
    }

    public boolean hasCorrectNumberOfParameter(ProcessorEnum processor , int numOfParameters) {
        if (processor == null)
            throw new NullPointerException("Processor can' be null!");

        return processor.getNumberOfArg() == numOfParameters;
    }

    public ProcessorEnum getSupportedClass(String processorName) {
        for (ProcessorEnum p : ProcessorEnum.values()) {
            if (p.name().equals(processorName))
                 return p;
        }
        throw new IllegalArgumentException("Processor with name "+ processorName + " is not supported!");
    }
}
