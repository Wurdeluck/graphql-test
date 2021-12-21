package com.library.graphqltest.resolver.author;

import com.library.graphqltest.model.Author;
import com.library.graphqltest.model.Book;
import com.library.graphqltest.service.AuthorService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public class AuthorResolver implements GraphQLResolver<Author> {
  private AuthorService authorService;

  public AuthorResolver(AuthorService authorService) {
    this.authorService = authorService;
  }

  public Set<Book> books(Author author) {
    return authorService.getByIdWithAllBooks(author.getId()).getBooks();
  }
}
