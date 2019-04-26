package com.opus.graphql.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opus.graphql.model.Book;

public interface BookService {

	List<Book> findAllBooks();

	long countBooks();

	Book findById(Long id);

	List<Book> findAllBooksWithSorting(String sortOrder, String sortBy);

	List<Book> findByDate(Date createdDate);
	
	Book saveBook(Book book);

	List<Book> findAllBooks(Map<String, Object> where);

	List<Book> loadBookById(List<Long> bookIds);
}
