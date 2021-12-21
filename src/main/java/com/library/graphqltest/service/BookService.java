package com.library.graphqltest.service;

import com.library.graphqltest.exceptions.ResourceNotFoundException;
import com.library.graphqltest.model.Book;
import com.library.graphqltest.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

  final static Logger log = LoggerFactory.getLogger(BookService.class);

  private BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Book getById(UUID id) {
    log.info("Get Book by id: {}", id);
    return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", String.valueOf(id)));
  }

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public Book getByIdAllAuthors(UUID id) {
    log.info("Get Book with authors by id: {}", id);
    return bookRepository.findByIdAllAuthors(id).orElseThrow(() -> new ResourceNotFoundException("Book", String.valueOf(id)));
  }

}
