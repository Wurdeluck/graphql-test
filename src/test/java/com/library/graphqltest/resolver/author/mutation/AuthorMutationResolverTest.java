package com.library.graphqltest.resolver.author.mutation;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.library.graphqltest.GraphqlTestApplication;
import com.library.graphqltest.TestHelper;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.transaction.Transactional;

import java.io.IOException;
import java.text.MessageFormat;

import static com.library.graphqltest.TestHelper.GRAPHQL_MUTATION_REQUEST_PATH;
import static com.library.graphqltest.TestHelper.GRAPHQL_MUTATION_RESPONSE_PATH;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GraphqlTestApplication.class)
class AuthorMutationResolverTest {

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
    void saveAuthor() throws IOException, JSONException {
        String testName = "saveAuthor";
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(MessageFormat.format(GRAPHQL_MUTATION_REQUEST_PATH, testName));
        String expectedResponseBody = TestHelper.readFile(MessageFormat.format(GRAPHQL_MUTATION_RESPONSE_PATH, testName));
        Assertions.assertTrue(graphQLResponse.isOk());
        JSONAssert.assertEquals(expectedResponseBody, graphQLResponse.getRawResponse().getBody(), true);
    }
}