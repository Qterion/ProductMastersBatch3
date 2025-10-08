package Homework.five.medium;

public class Main {

  public static void main(String[] args) {
    // 1. Создайте ArrayList<DayOfWeek> и добавьте в него все дни недели
    java.util.ArrayList<DayOfWeek> days = new java.util.ArrayList<>();
    days.add(DayOfWeek.MONDAY);
    days.add(DayOfWeek.TUESDAY);
    days.add(DayOfWeek.WEDNESDAY);
    days.add(DayOfWeek.THURSDAY);
    days.add(DayOfWeek.FRIDAY);
    days.add(DayOfWeek.SATURDAY);
    days.add(DayOfWeek.SUNDAY);

    // 2. Выведите список всех дней недели в консоль
    System.out.println("All days of the week:");
    for (DayOfWeek day : days) {
      System.out.println(day);
    }

    // Проверка метода isWeekend
    System.out.println("\nWeekend check:");
    for (DayOfWeek day : days) {
      System.out.println(day + " is weekend: " + isWeekend(day));
    }
  }
  // 3. Реализуйте метод isWeekend(DayOfWeek day)
  public static boolean isWeekend(DayOfWeek day) {
    return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
  }

}
