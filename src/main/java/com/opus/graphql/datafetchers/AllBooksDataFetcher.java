package com.opus.graphql.datafetchers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opus.graphql.model.Book;
import com.opus.graphql.resolver.Query;
import com.opus.graphql.services.BookService;
import com.opus.graphql.utility.SpringAutoWireHelper;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component("AllBookDataFetcher")
public class AllBooksDataFetcher implements DataFetcher<List<Book>> {

	@Autowired
	BookService bookService;

	public AllBooksDataFetcher() {
		System.out.println("Creating AllBooksDataFetcher ...");
	}

	@Override
	public List<Book> get(DataFetchingEnvironment env) {
		Map args = env.getArguments();
		if (args.containsKey("order") && args.containsKey("sortBy")){
			return bookService.findAllBooksWithSorting(String.valueOf(args.get("order")), String.valueOf(args.get("sortBy")));
		} else if (args.containsKey("createdDate")){
			return bookService.findByDate(new Date());
		}
		return bookService.findAllBooks();
	}

}
