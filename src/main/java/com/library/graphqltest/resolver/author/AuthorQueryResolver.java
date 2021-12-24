package com.library.graphqltest.resolver.author;

import com.library.graphqltest.entity.Author;
import com.library.graphqltest.services.AuthorService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class AuthorQueryResolver implements GraphQLQueryResolver {
  final static Logger log = LoggerFactory.getLogger(AuthorQueryResolver.class);
  private final AuthorService authorService;

  public AuthorQueryResolver(AuthorService authorService) {
    this.authorService = authorService;
  }

//  public Author getAuthorById(UUID id) {
//    return authorService.getById(id);
//  }

  public Author getAuthor(String name) {
    log.info(MessageFormat.format("Get author by name: {0}", name));
    return authorService.getByName(name);
  }

}