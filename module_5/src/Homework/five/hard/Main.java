package Homework.five.hard;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    // 1. Считывает из консоли n целых чисел и добавляет их в ArrayList<Integer>
    Scanner scanner = new Scanner(System.in);
    
    System.out.print("Введите количество чисел (n): ");
    int n = scanner.nextInt();
    
    ArrayList<Integer> numbers = new ArrayList<>();
    
    System.out.println("Введите " + n + " целых чисел:");
    for (int i = 0; i < n; i++) {
      int number = scanner.nextInt();
      numbers.add(number);
    }
    
    System.out.println("\nИсходный список: " + numbers);
    
    // Вызов метода removeDuplicates
    ArrayList<Integer> uniqueNumbers = removeDuplicates(numbers);
    
    System.out.println("Список без дубликатов: " + uniqueNumbers);
    
    scanner.close();
  }

  // 2. Метод removeDuplicates удаляет дубликаты, сохраняя порядок элементов
  public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) {
    ArrayList<Integer> result = new ArrayList<>();
    
    for (Integer number : list) {
      if (!result.contains(number)) {
        result.add(number);
      }
    }
    
    return result;
  }
}
