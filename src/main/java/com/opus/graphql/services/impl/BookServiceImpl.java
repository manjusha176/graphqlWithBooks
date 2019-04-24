package com.opus.graphql.services.impl;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.opus.graphql.datafetchers.AllBooksDataFetcher;
import com.opus.graphql.datafetchers.BookDataFetcher;
import com.opus.graphql.model.Book;
import com.opus.graphql.repo.BookRepository;
import com.opus.graphql.services.BookService;
import com.opus.graphql.utility.GenericDataFetcher;

import graphql.annotations.annotationTypes.GraphQLDataFetcher;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;

@Service
@GraphQLName("Query")
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookRepository;
	
	@Override
	@GraphQLField
	@GraphQLDataFetcher(value = GenericDataFetcher.class, args = {"AllBookDataFetcher"})
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	@GraphQLField
	public long countBooks() {
		return bookRepository.count();
	}

	@Override
	@GraphQLField
	@GraphQLDataFetcher(value = GenericDataFetcher.class, args = {"BookDataFetcher"})
	public Book findById(@NotNull @GraphQLName("id") Long id) {
		return bookRepository.findById(id).get();
	}

	@Override
	@GraphQLField
	@GraphQLDataFetcher(value = GenericDataFetcher.class, args = {"AllBookDataFetcher"})
	public List<Book> findAllBooksWithSorting(String sortOrder, String sortBy) {
		return bookRepository.findInSortedOrder(new Sort(Sort.Direction.fromString(sortOrder), sortBy));
	}

	@Override
	@GraphQLField
	@GraphQLDataFetcher(value = GenericDataFetcher.class, args = {"AllBookDataFetcher"})
	public List<Book> findByDate(Date createdDate) {
		return bookRepository.findByDate(createdDate);
	}

	@Override
	@GraphQLField
	@GraphQLDataFetcher(value = GenericDataFetcher.class, args = {"BookDataFetcher"})
	public Book saveBook(@NotNull @GraphQLName("book") Book book) {
		bookRepository.save(book);
		return bookRepository.findById(book.getId()).get();
	}

}
