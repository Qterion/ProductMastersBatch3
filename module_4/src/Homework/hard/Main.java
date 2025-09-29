package Homework.hard;

import Homework.medium.MyData;

public class Main {

  public static void main(String[] args) {
    // Create a Box with String type
    Box<String> stringBox = new Box<>("Hello, World!");
    System.out.println("String Box content: " + stringBox.getItem());
    stringBox.showType();

    // Change the value in the String Box
    stringBox.setItem("New String Value");
    System.out.println("Updated String Box content: " + stringBox.getItem());

    // Create a Box with MyData type
    Box<MyData> myDataBox = new Box<>(new MyData(1, "Test Description"));
    System.out.println("MyData Box content: " + myDataBox.getItem());
    myDataBox.showType();

    // Create a Box with Integer type
    Box<Integer> intBox = new Box<>(42);
    System.out.println("Integer Box content: " + intBox.getItem());
    intBox.showType();
  }

}
