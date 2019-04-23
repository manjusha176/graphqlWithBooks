package com.opus.graphql.resolver;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.opus.graphql.model.Book;
import com.opus.graphql.repo.BookRepository;

@Component
public class Query implements GraphQLQueryResolver {

	private BookRepository bookRepository;

	public Query(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	public long countBooks() {
		return bookRepository.count();
	}

	public Book findById(Long id) {
		return bookRepository.findById(id).get();
	}

	public List<Book> findAllBooksWithSorting(String sortOrder, String sortBy) {
		return bookRepository.findInSortedOrder(new Sort(Sort.Direction.fromString(sortOrder), sortBy));
	}

	public List<Book> findByDate(Date createdDate) {
		return bookRepository.findByDate(createdDate);
	}
}
