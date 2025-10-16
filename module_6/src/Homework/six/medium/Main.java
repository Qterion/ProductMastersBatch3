package Homework.six.medium;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
    String fileName = "Homework/six/medium/words.txt";
    
    // HashSet to store unique words
    HashSet<String> uniqueWords = new HashSet<>();
    
    // HashMap to count word frequency
    HashMap<String, Integer> wordFrequency = new HashMap<>();
    
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      
      while ((line = reader.readLine()) != null) {
        // Split line into words and process each word
        String[] words = line.split("\\s+");
        
        for (String word : words) {
          // Clean word: remove punctuation and convert to lowercase
          String cleanWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
          
          // Skip empty strings
          if (!cleanWord.isEmpty()) {
            // Add to HashSet (unique words)
            uniqueWords.add(cleanWord);
            
            // Count frequency in HashMap
            wordFrequency.put(cleanWord, wordFrequency.getOrDefault(cleanWord, 0) + 1);
          }
        }
      }
      
      // Display results
      System.out.println("=== Unique Words (HashSet) ===");
      System.out.println("Total unique words: " + uniqueWords.size());
      System.out.println("Unique words: " + uniqueWords);
      System.out.println();
      
      System.out.println("=== Word Frequency (HashMap) ===");
      for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
        System.out.println(entry.getKey() + ": " + entry.getValue());
      }
      
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
  }
}
