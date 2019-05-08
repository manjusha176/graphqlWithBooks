package com.opus.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.opus.graphql.model.Author;
import com.opus.graphql.model.Book;
import com.opus.graphql.repo.AuthorRepository;

@Component
public class BookResolver implements GraphQLResolver<Book> {
	@Autowired
    private AuthorRepository authorRepository;

    public Author getAuthor(Book book) {
    	System.out.println("-->"+book.getAuthor().getId());
    	System.out.println("id-->"+book.getAuthor().getId());
    	System.out.println("author-->"+authorRepository.findById(book.getAuthor().getId()));
        return authorRepository.findById(book.getAuthor().getId()).get();
    }
}
