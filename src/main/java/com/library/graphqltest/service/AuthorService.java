package com.library.graphqltest.service;

import com.library.graphqltest.exceptions.ResourceNotFoundException;
import com.library.graphqltest.model.Author;
import com.library.graphqltest.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {
  final static Logger log = LoggerFactory.getLogger(AuthorService.class);
  private AuthorRepository authorRepository;

  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public Author getById(UUID id) {
    log.info("Get Author by id: {}", id);
    return authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", String.valueOf(id)));
  }

  public Author getByName(String name) {
    log.info("Get Author by name: {}", name);
    return authorRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Author", name));
  }

  public Author getByIdWithAllBooks(UUID id) {
    log.info("Get Author with all books by id: {}", id);
    return authorRepository.findByIdAllBooks(id).orElseThrow(() -> new ResourceNotFoundException("Author", String.valueOf(id)));
  }
}
