package com.library.graphqltest.services;

import com.library.graphqltest.dto.AuthorInput;
import com.library.graphqltest.entity.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorService {

  Author save(AuthorInput authorInput);

  Author getById(UUID id);

  Author getByName(String name);

}
