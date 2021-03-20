package cz.seznam.fulltext.robot;

import cz.seznam.fulltext.robot.services.processors.IProcessor;
import cz.seznam.fulltext.robot.utils.readers.DataReader;
import cz.seznam.fulltext.robot.validators.CommandLineValidator;

import java.io.InputStreamReader;


/**
 * Your task is to implement the following application to the best of your abilities:
 *
 * <p>The application reads lines from standard input, processes them using one of the supported
 * processors, and writes the results to standard output.
 *
 * <p>Lines read from standard input must have the following format to be considered valid:
 *
 * <pre>
 * &lt;url&gt;\t&lt;content-type&gt;\t&lt;click count&gt;
 * </pre>
 *
 * <p>That is, a valid input line contains three values separated by a TAB character, where
 *
 * <ul>
 *   <li><code>url</code> is a string representing a URL (no need to validate whether the string is
 *       actually a valid URL for the sake of this exercise),
 *   <li><code>content-type</code> is a string representing a HTTP content type (again, no need to
 *       validate whether the string represents a valid content type),
 *   <li><code>click-count</code> is a positive integer representing number of times a particular
 *       URL was clicked on (e.g. in search engine results).
 * </ul>
 *
 * <p>An example of one input line:
 *
 * <pre>
 * https://seznam.cz\ttext/html\t100
 * </pre>
 *
 * <p>You can assume that no URL will appear more than once in the input.
 *
 * <p>The application should be invoked as follows (this example assumes unix-like shell is used):
 *
 * <pre>
 * $ cat input.txt | java -classpath . cz.seznam.fulltext.robot.Runner &lt;className&gt; [&lt;processor parameters&gt;]
 * </pre>
 *
 * <p>Structure of the application's output depends on the processor <code>className</code> used:
 *
 * <ul>
 *   <li><code>Top</code> - outputs top 10 URLs with highest click count, in the form
 *       &lt;url&gt;\t&lt;click-count&gt;</code> (where click-count is a positive integer), sorted
 *       from highest click count to lowest click count
 *   <li><code>ContentType</code> - for each content type mentioned in the input, outputs a number
 *       of URLs having that content type, using the form <code>
 *       &lt;content-type&gt;\t&lt;url-count&gt;</code> (where url-count is a positive integer),
 *       sorted alphabetically by content type
 *   <li><code>Grep</code> - outputs all lines in the input that match given regular expression
 *       (this processor requires one parameter on the command line - a regular expression)
 * </ul>
 *
 * <p>The skeleton code below is here to get you started. Feel free to re-design the code as you see
 * fit.
 *
 * <p>Make sure the resulting code is easily testable, and if time permits, write a couple of tests
 * using a test automation tool of your choice.
 *
 * <p>Try to minimize the application's memory footprint and CPU usage.
 */
public class Runner {
  private static final String PATH_TO_PROCESSORS = "cz.seznam.fulltext.robot.services.processors.";
  private static final String PROCESSOR_NAME_ENDING = "Processor";

  // more threads?
  public static void main(String[] args) throws Exception {
    CommandLineValidator validator = new CommandLineValidator(args);
    validator.checkArgs();

    String className = PATH_TO_PROCESSORS+args[0]+PROCESSOR_NAME_ENDING;

    IProcessor processor = (IProcessor) Class
            .forName(className)
            .getDeclaredConstructor(args.getClass())
            .newInstance((Object) args);

    DataReader.readAndProcess(new InputStreamReader(System.in),processor);
    processor.writeOutput();
  }
}
