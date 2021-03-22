package cz.seznam.fulltext.robot.utils.readers;

import cz.seznam.fulltext.robot.services.processors.IProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class DataReader {
    private static int BUFFER_SIZE = 8192;

    /**
     * Read from the passed reader line by line and pass the line to the given processor to process it.
     * Uses for reader by default buffer size of 8192 bytes.
     * @param reader the source to be read from.
     * @param processor process the given line
     * @throws IOException when an I/O error occurs
     * @throws NullPointerException when reader or processor passed as parameter are null
     */
    public static void readAndProcess(Reader reader, IProcessor processor, Integer buffer_size) throws IOException {
        if (buffer_size == null)
            buffer_size = BUFFER_SIZE;

        if (reader == null || processor == null)
            throw new NullPointerException("reader or processor can not be null!");

        String line;
        try(BufferedReader f = new BufferedReader(reader, buffer_size)) {
            while( (line = f.readLine()) != null )
            {
                processor.process(line);
            }
        } catch (IOException exception){
            System.out.println("An exception happened when reading from input file "+ exception);
            throw exception;
        }
    }
}
