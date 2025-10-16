package Homework.six.easy;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(15, 3, 42, 8, 23, 4, 16, 8, 15);
        NumberList numberList = new NumberList(numbers);
        
        System.out.println("Original list: " + numberList.getNumbers());
        System.out.println();
        
        System.out.println("1. Minimum: " + numberList.findMinimum());
        System.out.println("2. Maximum: " + numberList.findMaximum());
        System.out.println("3. Sort ascending: " + numberList.sortAscending());
        System.out.println("4. Sort descending: " + numberList.sortDescending());
        
        int searchElement = 23;
        System.out.println("5. Contains " + searchElement + ": " + numberList.containsElement(searchElement));
        System.out.println("   Contains 100: " + numberList.containsElement(100));
        
        int filterValue = 10;
        System.out.println("6. Numbers greater than " + filterValue + ": " + numberList.filterGreaterThan(filterValue));
        System.out.println("7. Sum of all numbers: " + numberList.sumAll());
    }
}

