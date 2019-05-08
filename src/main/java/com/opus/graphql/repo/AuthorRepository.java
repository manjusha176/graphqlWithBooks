package com.opus.graphql.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.opus.graphql.model.Author;

public interface AuthorRepository extends MongoRepository<Author, Long> {

}
