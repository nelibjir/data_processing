package cz.seznam.fulltext.robot.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineValidatorTest {

    @Test
    void checkArgs() {
        String[] args = new String[]{"a1","a2", "a3"};
        CommandLineValidator cv = new CommandLineValidator(args);

        assertThrows(IllegalArgumentException.class, cv::check);

        args = new String[]{"Top"};
        cv = new CommandLineValidator(args);
        assertDoesNotThrow(cv::check);

        args = new String[]{"A1"};
        cv = new CommandLineValidator(args);
        assertThrows(IllegalArgumentException.class, cv::check);

        args = new String[]{"Grep", "wda"};
        cv = new CommandLineValidator(args);
        assertDoesNotThrow(cv::check);
    }
}