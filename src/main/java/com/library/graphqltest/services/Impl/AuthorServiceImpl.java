package com.library.graphqltest.services.Impl;

import com.library.graphqltest.dto.AuthorInput;
import com.library.graphqltest.entity.Author;
import com.library.graphqltest.entity.Book;
import com.library.graphqltest.helper.MapperAuthor;
import com.library.graphqltest.repository.AuthorRepository;
import com.library.graphqltest.repository.BookRepository;
import com.library.graphqltest.services.AuthorService;
import com.library.graphqltest.services.BookService;
import graphql.GraphQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService {
  final static Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);
  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final MapperAuthor mapperAuthor;

  public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, MapperAuthor mapperAuthor) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.mapperAuthor = mapperAuthor;
  }

  @Override
  public Author getById(UUID id) {
    log.info("Get Author by id: {}", id);
    return authorRepository.findById(id).orElseThrow(() -> new GraphQLException("Author not found for id: " + id));
  }

  @Override
  public Author getByName(String name) {
    log.info("Get Author by name: {}", name);
    return authorRepository.findByName(name).orElseThrow(() -> new GraphQLException(MessageFormat.format("Author with name ''{0}'' not found", name)));
  }

  @Override
  public Author save(AuthorInput authorInput) {
    List<Book> books = findBooksById(authorInput.getBookIds());
    Author author = mapperAuthor.mapAuthorInputToAuthor(authorInput, books);
    return authorRepository.save(author);
  }

  public Author getByIdWithAllBooks(UUID id) {
    log.info("Get Author with all books by id: {}", id);
    return authorRepository.findByIdAllBooks(id).orElseThrow(() -> new GraphQLException("Author not found for id: " + id));
  }

  private List<Book> findBooksById(List<UUID> booksIds) {
    if (booksIds == null)
      return Collections.emptyList();
    List<Book> books = bookRepository.findAllById(booksIds);
    if (books.isEmpty())
      return Collections.emptyList();

    return books;
  }
}
