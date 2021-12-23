package com.library.graphqltest.resolver.book.mutation;


import com.library.graphqltest.dto.BookInput;
import com.library.graphqltest.entity.Book;
import com.library.graphqltest.resolver.author.mutation.AuthorMutationResolver;
import com.library.graphqltest.services.BookService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class BookMutationResolver implements GraphQLMutationResolver {
  final static Logger log = LoggerFactory.getLogger(BookMutationResolver.class);
  private final BookService bookService;

  public BookMutationResolver(BookService bookService) {
    this.bookService = bookService;
  }

  public Book saveBook(BookInput bookInput) {
    log.info(MessageFormat.format("Save book with title{0}", bookInput.getTitle()));
    return bookService.save(bookInput);
  }
}
