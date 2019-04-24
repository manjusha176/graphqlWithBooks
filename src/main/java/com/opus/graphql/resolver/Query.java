package com.opus.graphql.resolver;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.opus.graphql.datafetchers.AllBooksDataFetcher;
import com.opus.graphql.model.Book;
import com.opus.graphql.repo.BookRepository;

import graphql.annotations.annotationTypes.GraphQLDataFetcher;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;

@Component
@GraphQLName("Query")
public class Query implements GraphQLQueryResolver {

	private BookRepository bookRepository;

	public Query(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@GraphQLField
	@GraphQLDataFetcher(AllBooksDataFetcher.class)
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@GraphQLField
	public long countBooks() {
		return bookRepository.count();
	}

	@GraphQLField
	public Book findById(Long id) {
		return bookRepository.findById(id).get();
	}

	@GraphQLField
	@GraphQLDataFetcher(AllBooksDataFetcher.class)
	public List<Book> findAllBooksWithSorting(String sortOrder, String sortBy) {
		return bookRepository.findInSortedOrder(new Sort(Sort.Direction.fromString(sortOrder), sortBy));
	}

	@GraphQLField
	@GraphQLDataFetcher(AllBooksDataFetcher.class)
	public List<Book> findByDate(Date createdDate) {
		return bookRepository.findByDate(createdDate);
	}
}
