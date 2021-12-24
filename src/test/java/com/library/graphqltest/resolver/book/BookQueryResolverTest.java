package com.library.graphqltest.resolver.book;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.library.graphqltest.GraphqlTestApplication;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

import static com.library.graphqltest.TestHelper.GRAPHQL_QUERY_REQUEST_PATH;
import static com.library.graphqltest.TestHelper.GRAPHQL_QUERY_RESPONSE_PATH;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GraphqlTestApplication.class)
class BookQueryResolverTest {
    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;


    @Test
    @Transactional
    void getAllBooks() throws IOException, JSONException {
        String testName = "getAllBooks";
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(MessageFormat.format(GRAPHQL_QUERY_REQUEST_PATH, testName));
        String expectedResponseBody = readFile(MessageFormat.format(GRAPHQL_QUERY_RESPONSE_PATH, testName));
        Assertions.assertTrue(graphQLResponse.isOk());
        JSONAssert.assertEquals(expectedResponseBody, graphQLResponse.getRawResponse().getBody(), true);
    }

    @Test
    void getBooksByAuthor() throws IOException, JSONException {
        String testName = "getBooksByAuthor";
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(MessageFormat.format(GRAPHQL_QUERY_REQUEST_PATH, testName));
        String expectedResponseBody = readFile(MessageFormat.format(GRAPHQL_QUERY_RESPONSE_PATH, testName));
        Assertions.assertTrue(graphQLResponse.isOk());
        JSONAssert.assertEquals(expectedResponseBody, graphQLResponse.getRawResponse().getBody(), true);
    }

    private String readFile(String location) throws IOException {
        return new String(new ClassPathResource(location).getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}