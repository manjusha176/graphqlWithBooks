package com.opus.graphql.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.opus.graphql.model.Book;
import com.opus.graphql.repo.BookRepository;
import com.opus.graphql.services.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookRepository;
	
	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public long countBooks() {
		return bookRepository.count();
	}

	@Override
	public Book findById(Long id) {
		return bookRepository.findById(id).get();
	}

	@Override
	public List<Book> findAllBooksWithSorting(String sortOrder, String sortBy) {
		return bookRepository.findInSortedOrder(new Sort(Sort.Direction.fromString(sortOrder), sortBy));
	}

	@Override
	public List<Book> findByDate(Date createdDate) {
		return bookRepository.findByDate(createdDate);
	}

}
