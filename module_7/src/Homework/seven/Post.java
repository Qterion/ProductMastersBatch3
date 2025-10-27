package Homework.seven;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final int id;
  private final String author;
  private final String content;
  private final LocalDateTime createdAt;
  private int likes;
  private int reposts;
  private final List<Comment> comments = new ArrayList<>();

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

  public void addComment(Comment comment) {
    this.comments.add(comment);
  }

  public List<Comment> getComments() {
    return new ArrayList<>(comments);
  }

  public int getCommentCount() {
    return comments.size();
  }

  @Override
  public String toString() {
    String result = String.format("Post{id=%d, author='%s', content='%s', likes=%d, reposts=%d}", 
                         id, author, content, likes, reposts);
    if (!comments.isEmpty()) {
      result += String.format(" [%d comments]", comments.size());
    }
    return result;
  }

  public String toStringWithComments() {
    StringBuilder sb = new StringBuilder(toString());
    if (!comments.isEmpty()) {
      sb.append("\nComments:");
      for (Comment comment : comments) {
        sb.append("\n").append(comment);
      }
    }
    return sb.toString();
  }
}
