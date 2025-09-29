package Homework.easy;

/**
 * A generic interface for printing values of type T.
 *
 * @param <T> the type of values to be printed
 */
public interface Printer<T> {
    /**
     * Prints the given value.
     *
     * @param value the value to be printed
     */
    void print(T value);
}