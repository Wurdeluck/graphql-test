package com.library.graphqltest.repository;

import com.library.graphqltest.entity.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
//  @EntityGraph(value = "author-entity-graph")
  @Query(name = "Author.findWithAllBooks",
          value = "SELECT a FROM #{#entityName} a LEFT JOIN FETCH a.books WHERE a.id = ?1 ")
  Optional<Author> findById(UUID id);

  Optional<Author> findByName(String name);

  @Query(name = "Author.findWithAllBooks",
  value = "SELECT a FROM #{#entityName} a LEFT JOIN FETCH a.books WHERE a.id = ?1 ")
  Optional<Author> findByIdAllBooks(UUID id);
}
