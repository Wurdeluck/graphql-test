package com.library.graphqltest.resolver.book.mutation;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.library.graphqltest.GraphqlTestApplication;
import com.library.graphqltest.TestHelper;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.io.IOException;
import java.text.MessageFormat;

import static com.library.graphqltest.TestHelper.GRAPHQL_MUTATION_REQUEST_PATH;
import static com.library.graphqltest.TestHelper.GRAPHQL_MUTATION_RESPONSE_PATH;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GraphqlTestApplication.class)
class BookMutationResolverTest {

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    @Transactional
    void saveBook() throws IOException, JSONException {
        String testName = "saveBook";
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(MessageFormat.format(GRAPHQL_MUTATION_REQUEST_PATH, testName));
        String expectedResponseBody = TestHelper.readFile(MessageFormat.format(GRAPHQL_MUTATION_RESPONSE_PATH, testName));
        Assertions.assertTrue(graphQLResponse.isOk());
        JSONAssert.assertEquals(expectedResponseBody, graphQLResponse.getRawResponse().getBody(), true);
    }
}