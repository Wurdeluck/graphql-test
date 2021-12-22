package com.library.graphqltest.services;

import com.library.graphqltest.dto.BookInput;
import com.library.graphqltest.entity.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {

  Book getById(UUID id);

  List<Book> getAll(Integer skip, Integer limit);

  Book save(BookInput bookInput);

}
