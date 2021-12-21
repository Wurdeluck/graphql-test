package com.library.graphqltest.exceptions;

import java.text.MessageFormat;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String id) {
    super(MessageFormat.format("Resource with id=''{0}'' was not found.", id));
  }
  public ResourceNotFoundException(String type, String id) {
    super(MessageFormat.format("{0} with id=''{1}'' was not found.", type, id));
  }
  public ResourceNotFoundException() {
    super("Resource was not found");
  }
}