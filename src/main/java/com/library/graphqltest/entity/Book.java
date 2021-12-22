package com.library.graphqltest.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

//@NamedEntityGraph(
//        name = "book-entity-graph",
//        attributeNodes = {
//                @NamedAttributeNode("id"),
//                @NamedAttributeNode("title"),
//                @NamedAttributeNode(value = "authors", subgraph = "authors-subgraph"),
//        },
//        subgraphs = {
//                @NamedSubgraph(
//                        name = "authors-subgraph",
//                        attributeNodes = {
//                                @NamedAttributeNode("id"),
//                                @NamedAttributeNode("name"),
//                                @NamedAttributeNode("books")
//                        }
//                )
//        }
//)
@Entity
@Table(name = "books")
public class Book {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
          name = "UUID",
          strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "title", nullable = false)
  private String title;

  @ManyToMany(mappedBy = "books")
  private List<Author> authors;

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

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }
}
