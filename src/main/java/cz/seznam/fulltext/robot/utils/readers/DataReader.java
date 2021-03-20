package cz.seznam.fulltext.robot.utils.readers;

import cz.seznam.fulltext.robot.services.processors.IProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class DataReader {
    private static int BUFFER_SIZE = 8192; // TODO later set in config file

    public static void readAndProcess(Reader reader, IProcessor processor) throws IOException {
        String line;
        try(BufferedReader f = new BufferedReader(reader, BUFFER_SIZE)) {
            while( (line = f.readLine()) != null )
            {
                processor.process(line);
            }
        } catch (IOException exception){
            System.out.println("An exception happened hen reading from input file "+ exception);
            throw exception;
        }
    }
}
