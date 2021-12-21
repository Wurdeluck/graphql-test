package com.library.graphqltest.resolver;

import com.library.graphqltest.model.Author;
import com.library.graphqltest.model.Book;
import com.library.graphqltest.service.AuthorService;
import com.library.graphqltest.service.BookService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QueryResolver implements GraphQLQueryResolver {
  private BookService bookService;
  private AuthorService authorService;

  public QueryResolver(BookService bookService, AuthorService authorService) {
    this.bookService = bookService;
    this.authorService = authorService;
  }

  public Book book(UUID id) {
    return bookService.getById(id);
  }

  public Author author(UUID id) {
    return authorService.getById(id);
  }

  public Author author(String name) {
    return authorService.getByName(name);
  }
}