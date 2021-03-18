package cz.seznam.fulltext.robot.enums;

public enum ProcessorEnum {
    TOP(0),
    CONTENTTYPE(0),
    GREP (1);

    private final int numberOfArg;

    ProcessorEnum(int numberOfArg) {
        this.numberOfArg = numberOfArg;
    }

    public int getNumberOfArg() {
        return numberOfArg;
    }
}
