package Homework.six.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberList {
    private List<Integer> numbers;

    public NumberList(List<Integer> numbers) {
        this.numbers = numbers;
    }

    // Find minimum - returns the smallest number in the list
    public Integer findMinimum() {
        if (numbers.isEmpty()) {
            return null;
        }
        return Collections.min(numbers);
    }

    // Find maximum - returns the largest number in the list
    public Integer findMaximum() {
        if (numbers.isEmpty()) {
            return null;
        }
        return Collections.max(numbers);
    }

    // Sort ascending - returns a new list sorted from smallest to largest
    public List<Integer> sortAscending() {
        List<Integer> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        return sorted;
    }

    // Sort descending - returns a new list sorted from largest to smallest
    public List<Integer> sortDescending() {
        List<Integer> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted, Collections.reverseOrder());
        return sorted;
    }

    // Find element - checks if a given number exists in the list
    public boolean containsElement(Integer element) {
        return numbers.contains(element);
    }

    // Filter elements - returns a new list containing only numbers greater than a given value
    public List<Integer> filterGreaterThan(Integer value) {
        List<Integer> filtered = new ArrayList<>();
        for (Integer number : numbers) {
            if (number > value) {
                filtered.add(number);
            }
        }
        return filtered;
    }

    // Sum of all numbers - returns the sum of all elements
    public Integer sumAll() {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

    // Getter for the numbers list
    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }
}

