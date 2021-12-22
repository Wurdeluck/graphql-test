package com.library.graphqltest.resolver.book;

import com.library.graphqltest.entity.Author;
import com.library.graphqltest.entity.Book;
import com.library.graphqltest.services.AuthorService;
import com.library.graphqltest.services.BookService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BookQueryResolver implements GraphQLQueryResolver {
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
    return bookService.getAll(skip, limit);
  }

  public List<Book> getBooksByAuthor(UUID id) {
    return authorService.getById(id).getBooks();
  }
}