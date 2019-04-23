package com.opus.graphql.datafetchers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opus.graphql.model.Book;
import com.opus.graphql.resolver.Query;
import com.opus.graphql.services.BookService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>> {

	@Autowired
	BookService bookService;
	
	@Autowired
	Query query;

	// public List<Book> AllBooksDataFetcher() {
	// return bookService.findAllBooks();
	// }

	@Override
	public List<Book> get(DataFetchingEnvironment env) {
		Map args = env.getArguments();
		if (args.containsKey("order") && args.containsKey("sortBy")){
			return query.findAllBooksWithSorting(String.valueOf(args.get("order")), String.valueOf(args.get("sortBy")));
		} else if (args.containsKey("createdDate")){
			return query.findByDate(new Date());
		}
		return query.findAllBooks();
//		return bookService.findAllBooks();
	}

}
