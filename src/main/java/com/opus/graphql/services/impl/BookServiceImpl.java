package com.opus.graphql.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.introspector.PropertyUtils;

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
	public List<Book> findByDate(@NotNull @GraphQLName("createdDate") Date createdDate) {
		return bookRepository.findByDate(createdDate);
	}

	@Override
	@GraphQLField
	@GraphQLDataFetcher(value = GenericDataFetcher.class, args = {"BookDataFetcher"})
	public Book saveBook(@NotNull @GraphQLName("book") Book book) {
		bookRepository.save(book);
		return bookRepository.findById(book.getId()).get();
	}
	
	@Override
	@GraphQLField
	@GraphQLDataFetcher(value = GenericDataFetcher.class, args = {"AllBookDataFetcher"})
	public List<Book> findAllBooks(@NotNull @GraphQLName("where") Map<String, Object> where){
//		ExampleMatcher matcher = ExampleMatcher.matching();
//	    Book book = new Book();
//	    for (String key : where.keySet()) {
//	            matcher.withMatcher(key, ignoreCase());
//	            PropertyUtils.setSimpleProperty(book, key, where.get(key));
//	    }
//
//	    Example<User> example = Example.of(user, matcher);
//	    return userRepository.findAll(example);
		return bookRepository.findAll();
	}

	@Override
	public List<Book> loadBookById(List<Long> bookIds) {
		return (List<Book>) bookRepository.findAllById(bookIds);
	}

}
