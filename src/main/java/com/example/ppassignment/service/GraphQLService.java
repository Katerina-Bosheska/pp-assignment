package com.example.ppassignment.service;

import com.example.ppassignment.service.datafetchers.AllAuthorsDataFetcher;
import com.example.ppassignment.service.datafetchers.AllBooksDataFetcher;
import com.example.ppassignment.service.datafetchers.SpecificAuthorFetcher;
import com.example.ppassignment.service.datafetchers.SpecificBookFetcher;
import graphql.Scalars;
import graphql.schema.GraphQLSchema;
import graphql.GraphQL;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.hibernate.graph.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Value("classpath:books.graphql")
    Resource resource;

    private GraphQL graphQl;

    @Autowired
    private AllBooksDataFetcher allBooksDataFetcher;
    @Autowired
    private AllAuthorsDataFetcher allAuthorsDataFetcher;
    @Autowired
    private SpecificAuthorFetcher specificAuthorFetcher;
    @Autowired
    private SpecificBookFetcher specificBookFetcher;
    @Autowired
    private DateScalarConfiguration dateScalarConfiguration;

    @PostConstruct
    private void loadSchema() throws IOException {
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQl = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring(){
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allBooks", allBooksDataFetcher)
                        .dataFetcher("allAuthors", allAuthorsDataFetcher)
                        .dataFetcher("book", specificBookFetcher)
                        .dataFetcher("author", specificAuthorFetcher))
                .scalar(dateScalarConfiguration.dateScalar())
                .build();
    }

    public GraphQL getGraphQl(){
        return graphQl;
    }
}
