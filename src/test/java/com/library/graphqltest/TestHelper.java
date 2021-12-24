package com.library.graphqltest;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TestHelper {


    public static final String GRAPHQL_QUERY_REQUEST_PATH = "graphql/resolver/query/{0}/{0}.graphqls";
    public static final String GRAPHQL_QUERY_RESPONSE_PATH = "graphql/resolver/query/{0}/{0}.json";
    public static final String GRAPHQL_MUTATION_REQUEST_PATH = "graphql/resolver/mutation/{0}/{0}.graphqls";
    public static final String GRAPHQL_MUTATION_RESPONSE_PATH = "graphql/resolver/mutation/{0}/{0}.json";

    public static String readFile(String location) throws IOException {
        return new String(new ClassPathResource(location).getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
