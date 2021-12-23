package com.library.graphqltest.repository;

import com.library.graphqltest.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
@Query(name = "Book.findWithAllBooks",
        value = "SELECT b FROM #{#entityName} b LEFT JOIN FETCH b.authors WHERE b.id = ?1 ")
  Optional<Book> findById(UUID id);

  @Query(name = "Book.findWithAllBooks",
          value = "SELECT b FROM #{#entityName} b LEFT JOIN FETCH b.authors WHERE b.id = ?1 ")
  Optional<Book> findByIdAllAuthors(UUID id);

  @Query(name = "Book.findAllPaged",
          value = "SELECT * FROM public.books ORDER BY title OFFSET ?1*?2 LIMIT ?2", nativeQuery = true)
  List<Book> getAllPaged(Integer skip, Integer limit);
}
