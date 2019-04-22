package com.opus.graphql.datafetchers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opus.graphql.model.Book;
import com.opus.graphql.services.BookService;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
@GraphQLName("query")
public class BookDataFetcher implements DataFetcher<Book> {

	@Autowired
	BookService bookService;

	@Override
	 @GraphQLField
	public Book get(DataFetchingEnvironment env) {
		Map args = env.getArguments();
		return bookService.findById(new Long(String.valueOf(args.get("id"))));
	}

}
