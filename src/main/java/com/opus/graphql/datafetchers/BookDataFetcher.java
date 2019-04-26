package com.opus.graphql.datafetchers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opus.graphql.dataloader.BookBatchLoader;
import com.opus.graphql.model.Book;
import com.opus.graphql.services.BookService;
import com.opus.graphql.utility.SpringAutoWireHelper;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component("BookDataFetcher")
public class BookDataFetcher implements DataFetcher<CompletableFuture<Book>> {

	@Autowired
	BookService bookService;
	
	public BookDataFetcher() {
		System.out.println("Creating BookDataFetcher ...");
	}

	@Override
	public CompletableFuture<Book> get(DataFetchingEnvironment env) {
		
		BatchLoader<Long, Book> bookBatchLoader = new BatchLoader<Long, Book>() {
			@Override
			public CompletionStage<List<Book>> load(List<Long> bookIds) {
				return CompletableFuture.supplyAsync(() -> {
		            return bookService.loadBookById(bookIds);
		        });
			}
        };
		DataLoader<Long, Book> bookLoader = DataLoader.newDataLoader(bookBatchLoader);
		
		Map args = env.getArguments();
//		if(args.containsKey("book")) {
//			Map bookMap = (Map) args.get("book");
//			Book book = new Book();
//			book.setId((Long) bookMap.get("id"));
//			book.setTitle((String) bookMap.get("title"));
//			return bookService.saveBook(book);
//		}
		return bookLoader.load(new Long(String.valueOf(args.get("id"))));
//		return bookService.findById(new Long(String.valueOf(args.get("id"))));
	}

}
