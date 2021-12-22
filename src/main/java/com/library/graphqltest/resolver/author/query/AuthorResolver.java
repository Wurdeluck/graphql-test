package com.library.graphqltest.resolver.author.query;

import com.library.graphqltest.entity.Author;
import com.library.graphqltest.entity.Book;
import com.library.graphqltest.services.BookService;
import graphql.kickstart.tools.GraphQLResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorResolver implements GraphQLResolver<Book> {
  final static Logger log = LoggerFactory.getLogger(AuthorResolver.class);
  private final BookService bookService;

  public AuthorResolver(BookService bookService) {
    this.bookService = bookService;
  }

  public List<Author> authors(Book book) {
    log.info("Requesting authors for book with id: {}", book.getId());
    return bookService.getById(book.getId()).getAuthors();
  }
}
