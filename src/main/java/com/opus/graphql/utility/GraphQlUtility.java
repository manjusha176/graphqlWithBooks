package com.opus.graphql.utility;

import static graphql.GraphQL.newGraphQL;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.opus.graphql.datafetchers.AllBooksDataFetcher;
import com.opus.graphql.datafetchers.BookDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class GraphQlUtility {

	@Value("classpath:book.graphqls")
	private Resource schemaResource;
	private GraphQL graphQL;
	@Autowired
	private AllBooksDataFetcher allBooksDataFetcher;
	@Autowired
	private BookDataFetcher bookDataFetcher;

//	@Autowired
//	GraphQlUtility(AllBooksDataFetcher allBooksDataFetcher, BookDataFetcher bookDataFetcher) throws IOException {
//		this.allBooksDataFetcher = allBooksDataFetcher;
//		this.bookDataFetcher = bookDataFetcher;
//	}

	@PostConstruct
	public GraphQL createGraphQlObject() throws IOException {
		File schemas = schemaResource.getFile();
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemas);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		return newGraphQL(schema).build();
	}

	public RuntimeWiring buildRuntimeWiring() {
		return newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring.dataFetcher("findAllBooks", allBooksDataFetcher)
						.dataFetcher("findAllBooksWithSorting", allBooksDataFetcher)
						.dataFetcher("findByDate", allBooksDataFetcher).dataFetcher("findById", bookDataFetcher))
				.build();
	}
}
