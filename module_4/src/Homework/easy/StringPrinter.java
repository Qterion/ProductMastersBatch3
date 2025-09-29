package Homework.easy;

/**
 * A class that implements the Printer interface for String values.
 * This class prints strings to the console.
 */
public class StringPrinter implements Printer<String> {
    
    /**
     * Prints the given string value to the console.
     *
     * @param value the string to be printed
     */
    @Override
    public void print(String value) {
        System.out.println(value);
    }
}