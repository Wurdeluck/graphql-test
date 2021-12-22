package com.library.graphqltest.helper;

import com.library.graphqltest.dto.AuthorInput;
import com.library.graphqltest.entity.Author;
import com.library.graphqltest.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperAuthor {
  public Author mapAuthorInputToAuthor(AuthorInput authorInput, List<Book> books) {
    Author author = new Author();
    author.setName(authorInput.getName());
    if (!books.isEmpty())
      author.setBooks(books);
    return author;
  }
}
