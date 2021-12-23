package com.library.graphqltest.resolver.author;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.library.graphqltest.GraphqlTestApplication;
import com.library.graphqltest.TestContainerConfig;
import org.json.JSONException;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = GraphqlTestApplication.class)
class AuthorQueryResolverIntegrationTest {
  @Autowired
  GraphQLTestTemplate graphQLTestTemplate;

  @ClassRule
  public static PostgreSQLContainer<TestContainerConfig> postgreSQLContainer = TestContainerConfig.getInstance();

  private static final String GRAPHQL_QUERY_REQUEST_PATH = "graphql/resolver/query/{}.graphqls";
  private static final String GRAPHQL_QUERY_RESPONSE_PATH = "graphql/resolver/query/{}.json";

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