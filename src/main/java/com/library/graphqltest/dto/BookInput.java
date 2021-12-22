package com.library.graphqltest.dto;

import java.util.List;
import java.util.UUID;

public class BookInput {
  private String title;
  private List<UUID> authorIds;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<UUID> getAuthorIds() {
    return authorIds;
  }

  public void setAuthorIds(List<UUID> authorIds) {
    this.authorIds = authorIds;
  }
}
