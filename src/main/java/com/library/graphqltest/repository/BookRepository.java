package com.library.graphqltest.repository;

import com.library.graphqltest.model.Author;
import com.library.graphqltest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
  @Query(name = "Book.findWithAllBooks",
          value = "SELECT b FROM #{#entityName} b LEFT JOIN FETCH b.authors WHERE b.id = ?1 ")
  public Optional<Book> findByIdAllAuthors(UUID id);
}
