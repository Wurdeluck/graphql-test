package com.library.graphqltest.resolver.author.mutation;

import com.library.graphqltest.dto.AuthorInput;
import com.library.graphqltest.entity.Author;
import com.library.graphqltest.services.AuthorService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class AuthorMutationResolver implements GraphQLMutationResolver {
  final static Logger log = LoggerFactory.getLogger(AuthorMutationResolver.class);
  private final AuthorService authorService;

  public AuthorMutationResolver(AuthorService authorService) {
    this.authorService = authorService;
  }

  public Author saveAuthor(AuthorInput authorInput) {
    log.info(MessageFormat.format("Saving author with name{0}", authorInput.getName()));
    return authorService.save(authorInput);
  }
}
