package com.library.graphqltest.resolver.book.mutation;


import com.library.graphqltest.dto.BookInput;
import com.library.graphqltest.entity.Book;
import com.library.graphqltest.services.BookService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class BookMutationResolver implements GraphQLMutationResolver {

  private final BookService bookService;

  public BookMutationResolver(BookService bookService) {
    this.bookService = bookService;
  }

  public Book saveBook(BookInput bookInput) {
    return bookService.save(bookInput);
  }
}
