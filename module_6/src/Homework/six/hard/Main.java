package Homework.six.hard;

public class Main {

  public static void main(String[] args) {
    System.out.println("=== SafeList Demo ===\n");
    
    // Create SafeList
    SafeList<String> list = new SafeList<>();
    
    // Add elements
    System.out.println("Adding 'apple': " + list.add("apple"));
    System.out.println("Adding 'banana': " + list.add("banana"));
    System.out.println("Adding 'orange': " + list.add("orange"));
    
    // Try to add null (should not be added)
    System.out.println("\nAdding null: " + list.add(null));
    
    // Try to add duplicate (should not be added)
    System.out.println("Adding duplicate 'apple': " + list.add("apple"));
    
    System.out.println("\nCurrent list: " + list);
    System.out.println("List size: " + list.size());
    
    // Access valid indices
    System.out.println("\nAccessing valid indices:");
    System.out.println("get(0): " + list.get(0));  // apple
    System.out.println("get(1): " + list.get(1));  // banana
    System.out.println("get(2): " + list.get(2));  // orange
    
    // Access invalid index (returns null, no error)
    System.out.println("\nAccessing invalid index:");
    System.out.println("get(5): " + list.get(5));   // null (no error!)
    System.out.println("get(-1): " + list.get(-1)); // null (no error!)
    
    // Other operations
    System.out.println("\nOther operations:");
    System.out.println("Contains 'banana': " + list.contains("banana"));
    System.out.println("Contains 'grape': " + list.contains("grape"));
    System.out.println("Index of 'orange': " + list.indexOf("orange"));
    
    // Remove element
    System.out.println("\nRemoving 'banana': " + list.remove("banana"));
    System.out.println("List after removal: " + list);
    
    // Add at specific index
    System.out.println("\nAdding 'mango' at index 1:");
    list.add(1, "mango");
    System.out.println("List: " + list);
    
    // Try to add duplicate at index (should not work)
    System.out.println("\nTrying to add duplicate 'apple' at index 2:");
    list.add(2, "apple");
    System.out.println("List (unchanged): " + list);
    
    // Test with Integer SafeList
    System.out.println("\n=== SafeList<Integer> Demo ===");
    SafeList<Integer> numbers = new SafeList<>();
    numbers.add(10);
    numbers.add(20);
    numbers.add(30);
    numbers.add(null);  // Not added
    numbers.add(20);    // Duplicate, not added
    
    System.out.println("Numbers list: " + numbers);
    System.out.println("get(1): " + numbers.get(1));
    System.out.println("get(10): " + numbers.get(10)); // null, no error
  }
}
