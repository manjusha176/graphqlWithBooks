package com.opus.graphql.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.opus.graphql.datafetchers.AllBooksDataFetcher;
import com.opus.graphql.model.Book;
import com.opus.graphql.repo.BookRepository;
import com.opus.graphql.services.BookService;

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
	@GraphQLDataFetcher(AllBooksDataFetcher.class)
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
	public Book findById(Long id) {
		return bookRepository.findById(id).get();
	}

	@Override
	@GraphQLField
	@GraphQLDataFetcher(AllBooksDataFetcher.class)
	public List<Book> findAllBooksWithSorting(String sortOrder, String sortBy) {
		return bookRepository.findInSortedOrder(new Sort(Sort.Direction.fromString(sortOrder), sortBy));
	}

	@Override
	@GraphQLField
	@GraphQLDataFetcher(AllBooksDataFetcher.class)
	public List<Book> findByDate(Date createdDate) {
		return bookRepository.findByDate(createdDate);
	}

}
