package com.library.graphqltest.resolver.book;

import com.library.graphqltest.model.Author;
import com.library.graphqltest.model.Book;
import com.library.graphqltest.service.BookService;
import graphql.kickstart.tools.GraphQLResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BookResolver implements GraphQLResolver<Book> {
  final static Logger log = LoggerFactory.getLogger(BookResolver.class);
  private BookService bookService;

  public BookResolver(BookService bookService) {
    this.bookService = bookService;
  }

  public Set<Author> authors(Book book) {
    log.info("Requesting authors for book with id: {}", book.getId());
    return bookService.getByIdAllAuthors(book.getId()).getAuthors();
  }
}
