package Homework.seven;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Comment implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final String author;
  private final String text;
  private final LocalDateTime createdAt;

  public Comment(String author, String text) {
    this.author = author;
    this.text = text;
    this.createdAt = LocalDateTime.now();
  }

  public String getAuthor() {
    return author;
  }

  public String getText() {
    return text;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Override
  public String toString() {
    return String.format("  → %s: %s", author, text);
  }
}

