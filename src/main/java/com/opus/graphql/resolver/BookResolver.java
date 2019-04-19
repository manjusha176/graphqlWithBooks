package com.opus.graphql.resolver;

//import com.coxautodev.graphql.tools.GraphQLResolver;
//import com.opus.graphql.model.Author;
//import com.opus.graphql.model.Book;
//import com.opus.graphql.repo.AuthorRepository;
//
//public class BookResolver implements GraphQLResolver<Book> {
//    private AuthorRepository authorRepository;
//
//    public BookResolver(AuthorRepository authorRepository) {
//        this.authorRepository = authorRepository;
//    }
//
//    public Author getAuthor(Book book) {
//        return authorRepository.findById(book.getAuthor().getId()).get();
//    }
//}
