package com.library.graphqltest.resolver.author;

import com.library.graphqltest.entity.Author;
import com.library.graphqltest.services.AuthorService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorQueryResolver implements GraphQLQueryResolver {
  private final AuthorService authorService;

  public AuthorQueryResolver(AuthorService authorService) {
    this.authorService = authorService;
  }

//  public Author getAuthorById(UUID id) {
//    return authorService.getById(id);
//  }

  public Author getAuthor(String name) {
    return authorService.getByName(name);
  }

}