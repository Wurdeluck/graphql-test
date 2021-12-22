package com.library.graphqltest.resolver.author.mutation;

import com.library.graphqltest.dto.AuthorInput;
import com.library.graphqltest.entity.Author;
import com.library.graphqltest.services.AuthorService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class AuthorMutationResolver implements GraphQLMutationResolver {

  private final AuthorService authorService;

  public AuthorMutationResolver(AuthorService authorService) {
    this.authorService = authorService;
  }

  public Author saveAuthor(AuthorInput authorInput) {
    return authorService.save(authorInput);
  }
}
