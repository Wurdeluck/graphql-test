package com.library.graphqltest.model;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "books")
public class Book {
  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "title", nullable = false)
  private String title;

  @ManyToMany(mappedBy = "books")
  private Set<Author> authors;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Set<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<Author> authors) {
    this.authors = authors;
  }
}
