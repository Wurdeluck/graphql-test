package com.library.graphqltest.resolver.book.query;

import com.library.graphqltest.entity.Author;
import com.library.graphqltest.entity.Book;
import com.library.graphqltest.services.AuthorService;
import graphql.kickstart.tools.GraphQLResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookResolver implements GraphQLResolver<Author> {
  final static Logger log = LoggerFactory.getLogger(BookResolver.class);
  private final AuthorService authorService;

  public BookResolver(AuthorService authorService) {
    this.authorService = authorService;
  }

  public List<Book> books(Author author) {
    log.info("Requesting books for author with id: {}", author.getId());
    return authorService.getById(author.getId()).getBooks();
  }

}
