package Homework.seven;

import java.time.LocalDateTime;

public class Post {
  private final int id;
  private final String author;
  private final String content;
  private final LocalDateTime createdAt;
  private int likes;
  private int reposts;

  public Post(int id, String author, String content) {
    this.id = id;
    this.author = author;
    this.content = content;
    this.createdAt = LocalDateTime.now();
    this.likes = 0;
    this.reposts = 0;
  }

  public int getId() {
    return id;
  }

  public String getAuthor() {
    return author;
  }

  public String getContent() {
    return content;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public int getLikes() {
    return likes;
  }

  public int getReposts() {
    return reposts;
  }

  public void like() {
    this.likes++;
  }

  public void repost() {
    this.reposts++;
  }

  @Override
  public String toString() {
    return String.format("Post{id=%d, author='%s', content='%s', likes=%d, reposts=%d}", 
                         id, author, content, likes, reposts);
  }
}
