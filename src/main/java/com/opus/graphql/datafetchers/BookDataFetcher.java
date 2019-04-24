package com.opus.graphql.datafetchers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opus.graphql.model.Book;
import com.opus.graphql.services.BookService;
import com.opus.graphql.utility.SpringAutoWireHelper;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component("BookDataFetcher")
public class BookDataFetcher implements DataFetcher<Book> {

	@Autowired
	BookService bookService;
	
	public BookDataFetcher() {
		System.out.println("Creating BookDataFetcher ...");
	}

	@Override
	public Book get(DataFetchingEnvironment env) {
		Map args = env.getArguments();
		if(args.containsKey("book")) {
			Map bookMap = (Map) args.get("book");
			Book book = new Book();
			book.setId((Long) bookMap.get("id"));
			book.setTitle((String) bookMap.get("title"));
			return bookService.saveBook(book);
		}
		return bookService.findById(new Long(String.valueOf(args.get("id"))));
	}

}
