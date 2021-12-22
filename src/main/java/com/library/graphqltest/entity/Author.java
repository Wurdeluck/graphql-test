package com.library.graphqltest.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

//@NamedEntityGraph(
//  name = "author-entity-graph",
//  attributeNodes = {
//    @NamedAttributeNode("id"),
//    @NamedAttributeNode("name"),
//    @NamedAttributeNode(value = "books", subgraph = "books-subgraph"),
//  },
//  subgraphs = {
//    @NamedSubgraph(
//      name = "books-subgraph",
//      attributeNodes = {
//        @NamedAttributeNode("id"),
//        @NamedAttributeNode("title"),
//        @NamedAttributeNode("authors")
//      }
//    )
//  }
//)
@Entity
@Table(name = "authors")
public class Author {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
          name = "UUID",
          strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToMany
//  @ManyToMany(cascade = CascadeType.MERGE)
  @JoinTable(
          name = "authors_books",
          joinColumns = @JoinColumn(name = "author_id"),
          inverseJoinColumns = @JoinColumn(name = "book_id"))
  private List<Book> books;

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

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books)
  {
    this.books = books;
  }
}
