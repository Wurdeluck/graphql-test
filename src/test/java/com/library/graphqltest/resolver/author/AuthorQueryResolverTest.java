package com.library.graphqltest.resolver.author;

import com.library.graphqltest.TestContainersContainerConfig.PostgresContainer;
import com.library.graphqltest.entity.Author;
import com.library.graphqltest.repository.AuthorRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
class AuthorQueryResolverIntegrationTest {
  @Autowired
  private AuthorRepository authorRepository;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Container
  public static PostgresContainer postgresContainer = PostgresContainer.getInstance();

  @Test
  void getAuthor() {
    Author author = new Author();
    author.setName("TestAuthor");
    Author authorSaved = authorRepository.save(author);
    Assertions.assertEquals(author.getName(), authorSaved.getName());
  }
}