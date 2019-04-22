package com.opus.graphql.utility;

import static graphql.schema.GraphQLSchema.newSchema;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opus.graphql.datafetchers.AllBooksDataFetcher;
import com.opus.graphql.datafetchers.BookDataFetcher;
import com.opus.graphql.services.impl.BookServiceImpl;

import graphql.GraphQL;
import graphql.annotations.processor.GraphQLAnnotations;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;

@Component
public class GraphQlUtility {

	// @Value("classpath:book.graphqls")
	// private Resource schemaResource;
	// private GraphQL graphQL;
	@Autowired
	private AllBooksDataFetcher allBooksDataFetcher;
	@Autowired
	private BookDataFetcher bookDataFetcher;

	@PostConstruct
	public GraphQL createGraphQlObject() throws IOException {
		// File schemas = schemaResource.getFile();
		// TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemas);
		// RuntimeWiring wiring = buildRuntimeWiring();
		// GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		// return newGraphQL(schema).build();

		GraphQLObjectType object = GraphQLAnnotations.object(BookServiceImpl.class);
		GraphQLSchema schema = newSchema().query(object).build();
		return GraphQL.newGraphQL(schema).build();
	}

	public RuntimeWiring buildRuntimeWiring() {
		return newRuntimeWiring().type("Query", typeWiring -> typeWiring.dataFetcher("findAllBooks", allBooksDataFetcher).dataFetcher("findAllBooksWithSorting", allBooksDataFetcher).dataFetcher("findByDate", allBooksDataFetcher).dataFetcher("findById", bookDataFetcher)).scalar(ExtendedScalars.Date).build();
	}
}
