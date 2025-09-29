package Homework.hard;

/**
 * A generic class that represents a container for storing a single object of any type.
 *
 * @param <T> the type of the object to be stored in the container
 */
public class Box<T> {
    private T item;

    /**
     * Constructor that initializes the box with an initial value.
     *
     * @param item the initial value to be stored in the box
     */
    public Box(T item) {
        this.item = item;
    }

    /**
     * Sets a new value in the container.
     *
     * @param item the new value to be stored
     */
    public void setItem(T item) {
        this.item = item;
    }

    /**
     * Returns the current value stored in the container.
     *
     * @return the current value
     */
    public T getItem() {
        return item;
    }

    /**
     * Prints the type of the object stored in the container.
     */
    public void showType() {
        System.out.println("Type of the stored object: " + 
                (item != null ? item.getClass().getName() : "null"));
    }
}