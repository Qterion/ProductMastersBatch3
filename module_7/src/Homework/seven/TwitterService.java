package Homework.seven;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TwitterService {
  private final List<Post> posts = new ArrayList<>();
  private int nextPostId = 1;

  public void initializePosts() {
    posts.add(new Post(nextPostId++, "Alice", "Привет, мир!"));
    posts.add(new Post(nextPostId++, "Bob", "Сегодня отличный день!"));
    posts.add(new Post(nextPostId++, "Charlie", "Люблю программировать на Java."));
    System.out.println("Добавлены стартовые посты.");
  }

  public int createPost(String author, String content) {
    if (content.length() > 280) {
      content = content.substring(0, 280);
    }
    Post post = new Post(nextPostId++, author, content);
    posts.add(post);
    return post.getId();
  }

  public boolean likePost(int postId) {
    return posts.stream()
        .filter(p -> p.getId() == postId)
        .findFirst()
        .map(post -> {
          post.like();
          return true;
        })
        .orElse(false);
  }

  public boolean repostPost(int postId, String author) {
    return posts.stream()
        .filter(p -> p.getId() == postId)
        .findFirst()
        .map(post -> {
          post.repost();
          return createRepost(post, author);
        })
        .orElse(false);
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
}
