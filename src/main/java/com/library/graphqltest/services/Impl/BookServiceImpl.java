package com.library.graphqltest.services.Impl;

import com.library.graphqltest.dto.BookInput;
import com.library.graphqltest.entity.Author;
import com.library.graphqltest.exceptions.ResourceNotFoundException;
import com.library.graphqltest.entity.Book;
import com.library.graphqltest.helper.MapperBook;
import com.library.graphqltest.repository.AuthorRepository;
import com.library.graphqltest.repository.BookRepository;
import com.library.graphqltest.services.AuthorService;
import com.library.graphqltest.services.BookService;
import graphql.GraphQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

  final static Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
  final static Integer DEFAULT_SKIP = 0;
  final static Integer DEFAULT_LIMIT = 10;

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final MapperBook mapperBook;

  public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, MapperBook mapperBook) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
    this.mapperBook = mapperBook;
  }

  @Override
  public Book getById(UUID id) {
    log.info("Get Book by id: {}", id);
    return bookRepository.findById(id).orElseThrow(() -> new GraphQLException("Book not found. Id: " + id));
  }

  @Override
  public List<Book> getAll(Integer skip, Integer limit) {
    return bookRepository.getAllPaged(skip == null ? DEFAULT_SKIP : skip, limit == null ? DEFAULT_LIMIT : limit);
  }

  public Book getByIdAllAuthors(UUID id) {
    log.info("Get Book with authors by id: {}", id);
    return bookRepository.findByIdAllAuthors(id).orElseThrow(() -> new GraphQLException("Book not found. Id: " + id));
  }

  @Override
  public Book save(BookInput bookInput) {
    List<Author> authors = findAuthorsById(bookInput.getAuthorIds());
    Book book = mapperBook.mapBookInputToBook(bookInput, authors);
    return bookRepository.save(book);
  }
  private List<Author> findAuthorsById(List<UUID> authorsIds) {
    if (authorsIds == null)
      return Collections.emptyList();
    List<Author> authors = authorRepository.findAllById(authorsIds);
    if (authors.isEmpty())
      return Collections.emptyList();

    return authors;
  }

}
