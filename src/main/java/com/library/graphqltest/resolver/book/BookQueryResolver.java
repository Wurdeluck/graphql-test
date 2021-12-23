package com.library.graphqltest.resolver.book;

import com.library.graphqltest.entity.Author;
import com.library.graphqltest.entity.Book;
import com.library.graphqltest.resolver.author.mutation.AuthorMutationResolver;
import com.library.graphqltest.services.AuthorService;
import com.library.graphqltest.services.BookService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BookQueryResolver implements GraphQLQueryResolver {
  final static Logger log = LoggerFactory.getLogger(AuthorMutationResolver.class);
  private final BookService bookService;
  private final AuthorService authorService;

  public BookQueryResolver(BookService bookService, AuthorService authorService) {
    this.bookService = bookService;
    this.authorService = authorService;
  }

//  public Book getBookById(UUID id) {
//    return bookService.getById(id);
//  }

  public List<Book> getAllBooks(Integer skip, Integer limit) {
    log.info("Get all books with page info: skip:{}, limit:{}", skip, limit);
    return bookService.getAll(skip, limit);
  }

  public List<Book> getBooksByAuthor(UUID id) {
    log.info("Get books By Author with id: {}", id);
    return authorService.getById(id).getBooks();
  }
}