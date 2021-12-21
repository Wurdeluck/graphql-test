package com.library.graphqltest.model;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "authors")
public class Author {
  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToMany
  @JoinTable(
          name = "authors_books",
          joinColumns = @JoinColumn(name = "author_id"),
          inverseJoinColumns = @JoinColumn(name = "book_id"))
  private Set<Book> books;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Book> getBooks() {
    return books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }
}
