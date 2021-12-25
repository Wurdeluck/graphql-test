package com.library.graphqltest.resolver.author;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.library.graphqltest.GraphqlTestApplication;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

import static com.library.graphqltest.TestHelper.GRAPHQL_QUERY_REQUEST_PATH;
import static com.library.graphqltest.TestHelper.GRAPHQL_QUERY_RESPONSE_PATH;


@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GraphqlTestApplication.class)
class AuthorQueryResolverIntegrationTest {
  @Autowired
  GraphQLTestTemplate graphQLTestTemplate;

  @Container
  static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres");

  @DynamicPropertySource
  static void dynamicSource(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", () -> db.getJdbcUrl());
    registry.add("spring.datasource.username", () -> db.getUsername());
    registry.add("spring.datasource.password", () -> db.getPassword());
    registry.add("spring.jpa.database-platform", PostgreSQL9Dialect.class::getName);
  }

  @Test
  @Transactional
  void getAuthor() throws IOException, JSONException {
    String testName = "getAuthor";
    GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(MessageFormat.format(GRAPHQL_QUERY_REQUEST_PATH, testName));
    String expectedResponseBody = readFile(MessageFormat.format(GRAPHQL_QUERY_RESPONSE_PATH, testName));
    Assertions.assertTrue(graphQLResponse.isOk());
    JSONAssert.assertEquals(expectedResponseBody, graphQLResponse.getRawResponse().getBody(), true);
  }

  private String readFile(String location) throws IOException {
    return new String(new ClassPathResource(location).getInputStream().readAllBytes(), StandardCharsets.UTF_8);
  }
}