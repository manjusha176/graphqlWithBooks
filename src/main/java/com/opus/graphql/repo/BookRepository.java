package com.opus.graphql.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.opus.graphql.model.Book;

public interface BookRepository extends MongoRepository<Book, Long> {
	@Query ("{ 'id' : {$ne : null} }")
	List<Book> findInSortedOrder(Sort sort);

	@Query ("{ 'createdDate' : {$eq : ?0} }")
	List<Book> findByDate(Date createdDate);
}
