package com.library.graphqltest.dto;

import java.util.List;
import java.util.UUID;

public class AuthorInput {
  private String name;
  private List<UUID> bookIds;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<UUID> getBookIds() {
    return bookIds;
  }

  public void setBookIds(List<UUID> bookIds) {
    this.bookIds = bookIds;
  }
}
