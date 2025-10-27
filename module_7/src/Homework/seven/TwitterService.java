package Homework.seven;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TwitterService {
  private static final String POSTS_FILE = "posts.dat";
  private List<Post> posts = new ArrayList<>();
  private int nextPostId = 1;

  public void initializePosts() {
    if (!loadPosts()) {
      // Load default posts if file doesn't exist
      posts.add(new Post(nextPostId++, "Alice", "Привет, мир!"));
      posts.add(new Post(nextPostId++, "Bob", "Сегодня отличный день!"));
      posts.add(new Post(nextPostId++, "Charlie", "Люблю программировать на Java."));
      System.out.println("Добавлены стартовые посты.");
      savePosts();
    } else {
      System.out.println("Посты загружены из файла.");
    }
  }

  public void savePosts() {
    try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(POSTS_FILE))) {
      oos.writeObject(posts);
      oos.writeInt(nextPostId);
    } catch (IOException e) {
      System.err.println("Ошибка при сохранении постов: " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  public boolean loadPosts() {
    try (ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream(POSTS_FILE))) {
      posts = (List<Post>) ois.readObject();
      nextPostId = ois.readInt();
      return true;
    } catch (FileNotFoundException e) {
      return false;
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Ошибка при загрузке постов: " + e.getMessage());
      return false;
    }
  }

  public int createPost(String author, String content) {
    if (content.length() > 280) {
      content = content.substring(0, 280);
    }
    Post post = new Post(nextPostId++, author, content);
    posts.add(post);
    savePosts();
    return post.getId();
  }

  public boolean likePost(int postId) {
    boolean result = posts.stream()
        .filter(p -> p.getId() == postId)
        .findFirst()
        .map(post -> {
          post.like();
          return true;
        })
        .orElse(false);
    if (result) {
      savePosts();
    }
    return result;
  }

  public boolean repostPost(int postId, String author) {
    boolean result = posts.stream()
        .filter(p -> p.getId() == postId)
        .findFirst()
        .map(post -> {
          post.repost();
          return createRepost(post, author);
        })
        .orElse(false);
    if (result) {
      savePosts();
    }
    return result;
  }

  private boolean createRepost(Post originalPost, String author) {
    Post repost = new Post(nextPostId++, author, 
        "RT @" + originalPost.getAuthor() + ": " + originalPost.getContent());
    posts.add(repost);
    return true;
  }

  public List<Post> getAllPosts() {
    return posts.stream()
        .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
        .collect(Collectors.toList());
  }

  public List<Post> getPopularPosts(int count) {
    return posts.stream()
        .sorted(Comparator.comparing(Post::getLikes).reversed())
        .limit(count)
        .collect(Collectors.toList());
  }

  public List<Post> getUserPosts(String username) {
    return posts.stream()
        .filter(p -> p.getAuthor().equals(username))
        .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
        .collect(Collectors.toList());
  }

  public Post getPostById(int postId) {
    return posts.stream()
        .filter(p -> p.getId() == postId)
        .findFirst()
        .orElse(null);
  }

  public boolean addComment(int postId, String author, String text) {
    Post post = getPostById(postId);
    if (post != null) {
      post.addComment(new Comment(author, text));
      savePosts();
      return true;
    }
    return false;
  }

  public boolean deletePost(int postId, String author) {
    boolean removed = posts.removeIf(p -> p.getId() == postId && p.getAuthor().equals(author));
    if (removed) {
      savePosts();
    }
    return removed;
  }

  public boolean viewPostWithComments(int postId) {
    Post post = getPostById(postId);
    if (post != null) {
      System.out.println(post.toStringWithComments());
      return true;
    }
    return false;
  }
}
