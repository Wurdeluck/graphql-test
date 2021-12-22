package com.library.graphqltest.helper;

import com.library.graphqltest.dto.BookInput;
import com.library.graphqltest.entity.Author;
import com.library.graphqltest.entity.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperBook {
  public Book mapBookInputToBook(BookInput bookInput, List<Author> authors) {
    Book book = new Book();
    book.setTitle(bookInput.getTitle());
    if (!authors.isEmpty())
      book.setAuthors(authors);
    return book;
  }
}
