package com.opus.graphql;

import java.util.Date;

import javax.annotation.PostConstruct;

import static graphql.schema.GraphQLSchema.newSchema;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.opus.graphql.model.Author;
import com.opus.graphql.model.Book;
import com.opus.graphql.model.Category;
import com.opus.graphql.repo.AuthorRepository;
import com.opus.graphql.repo.BookRepository;
import com.opus.graphql.resolver.Query;

import graphql.GraphQL;
import graphql.annotations.processor.GraphQLAnnotations;
import graphql.annotations.processor.retrievers.GraphQLObjectHandler;
import graphql.schema.GraphQLObjectType;

@SpringBootApplication
public class Start {

	public static void main(String[] args) {
//		GraphQLAnnotations.getInstance().getTypeRegistry().clear();
		SpringApplication.run(Start.class, args);
	}

//	@Bean
//	public Query query(BookRepository bookRepository) {
//		return new Query(bookRepository);
//	}
	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, AuthorRepository authorRepo) {
		return (args) -> {
			
			authorRepo.save(new Author((long)21, "manjusha", "dhamdhere"));
			authorRepo.save(new Author((long)22, "abc", "xyz"));
			
			// 1st doc
			Author author = new Author((long) 21);
			bookRepository.save(new Book((long) 11, "Java: A Beginner's Guide, Sixth Edition", "0071809252", 728,
					author, new Date(),Category.GENERAL));

			// 2nd doc
			Author author1 = new Author((long) 22);
			bookRepository.save(new Book((long) 12, "GraphQL", "007172", 720, author1, new Date(),Category.GENERAL));
		};
	}

}
