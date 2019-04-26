package com.opus.graphql.dataloader;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.dataloader.BatchLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opus.graphql.model.Author;
import com.opus.graphql.model.Book;
import com.opus.graphql.services.BookService;

@Component
public class BookBatchLoader implements BatchLoader<Long, Book> {

	@Autowired
	BookService bookService;
	
	@Override
	public CompletionStage<List<Book>> load(List<Long> bookIds) {
		return CompletableFuture.supplyAsync(() -> {
            return bookService.loadBookById(bookIds);
        });
	}

}
