package Homework.seven;

import java.util.*;

public class TwitterConsoleApp {
  private static final Scanner scanner = new Scanner(System.in);
  private static final TwitterService twitterService = new TwitterService();
  private User currentUser;

  public static void main(String[] args) {
    new TwitterConsoleApp().run();
  }

  public void run() {
    System.out.print("Введите ваше имя: ");
    String userName = scanner.nextLine().trim();
    currentUser = new User(userName);
    System.out.println("Добро пожаловать, " + currentUser.getName() + "!");

    twitterService.initializePosts();

    while (true) {
      showMenu();
      int choice = getIntInput();
      switch (choice) {
        case 1:
          createPost();
          break;
        case 2:
          likePost();
          break;
        case 3:
          repostPost();
          break;
        case 4:
          showAllPosts();
          break;
        case 5:
          showPopularPosts();
          break;
        case 6:
          showUserPosts();
          break;
        case 7:
          addComment();
          break;
        case 8:
          deletePost();
          break;
        case 9:
          viewPostWithComments();
          break;
        case 10:
          System.out.println("Выход…");
          return;
        default:
          System.out.println("Некорректный ввод. Попробуйте снова.");
      }
    }
  }

  private void createPost() {
    System.out.print("Введите текст поста (макс. 280 символов): ");
    String content = scanner.nextLine().trim();
    if (content.length() > 280) {
      System.out.println("Текст обрезан до 280 символов.");
    }
    twitterService.createPost(currentUser.getName(), content);
    System.out.println("Пост добавлен!");
  }

  private void likePost() {
    System.out.print("Введите ID поста: ");
    int postId = getIntInput();
    if (twitterService.likePost(postId)) {
      System.out.println("Пост лайкнут!");
    } else {
      System.out.println("Пост с таким ID не найден.");
    }
  }

  private void repostPost() {
    System.out.print("Введите ID поста: ");
    int postId = getIntInput();
    if (twitterService.repostPost(postId, currentUser.getName())) {
      System.out.println("Пост зарепощен!");
    } else {
      System.out.println("Пост с таким ID не найден.");
    }
  }

  private void showAllPosts() {
    System.out.println("Все посты:");
    List<Post> posts = twitterService.getAllPosts();
    if (posts.isEmpty()) {
      System.out.println("Постов пока нет.");
    } else {
      posts.forEach(System.out::println);
    }
  }

  private void showPopularPosts() {
    System.out.print("Введите количество популярных постов: ");
    int count = getIntInput();
    List<Post> posts = twitterService.getPopularPosts(count);
    System.out.println("Популярные посты:");
    if (posts.isEmpty()) {
      System.out.println("Постов пока нет.");
    } else {
      posts.forEach(System.out::println);
    }
  }

  private void showUserPosts() {
    System.out.println("Мои посты:");
    List<Post> posts = twitterService.getUserPosts(currentUser.getName());
    if (posts.isEmpty()) {
      System.out.println("У вас пока нет постов.");
    } else {
      posts.forEach(System.out::println);
    }
  }

  private void addComment() {
    System.out.print("Введите ID поста: ");
    int postId = getIntInput();
    System.out.print("Введите текст комментария: ");
    String text = scanner.nextLine().trim();
    if (twitterService.addComment(postId, currentUser.getName(), text)) {
      System.out.println("Комментарий добавлен!");
    } else {
      System.out.println("Пост с таким ID не найден.");
    }
  }

  private void deletePost() {
    System.out.print("Введите ID поста для удаления: ");
    int postId = getIntInput();
    if (twitterService.deletePost(postId, currentUser.getName())) {
      System.out.println("Пост удален!");
    } else {
      System.out.println("Пост с таким ID не найден или вы не являетесь автором.");
    }
  }

  private void viewPostWithComments() {
    System.out.print("Введите ID поста: ");
    int postId = getIntInput();
    if (!twitterService.viewPostWithComments(postId)) {
      System.out.println("Пост с таким ID не найден.");
    }
  }

  private int getIntInput() {
    int input;
    try {
      input = Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("Некорректный ввод.");
      return -1;
    }
    return input;
  }

  private static void showMenu() {
    System.out.println("\n=== Twitter Console ===");
    System.out.println("1. Написать пост");
    System.out.println("2. Лайкнуть пост");
    System.out.println("3. Сделать репост");
    System.out.println("4. Показать все посты");
    System.out.println("5. Показать популярные посты");
    System.out.println("6. Показать мои посты");
    System.out.println("7. Добавить комментарий");
    System.out.println("8. Удалить пост");
    System.out.println("9. Показать пост с комментариями");
    System.out.println("10. Выход");
    System.out.print("Выберите действие: ");
  }

}
