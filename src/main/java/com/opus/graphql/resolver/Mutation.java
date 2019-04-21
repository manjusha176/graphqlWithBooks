package com.opus.graphql.resolver;
//
//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
//import com.opus.graphql.model.Author;
//import com.opus.graphql.model.Book;
//import com.opus.graphql.repo.BookRepository;
//
//public class Mutation implements GraphQLMutationResolver {
//	private BookRepository bookRepository;
//
//	public Mutation(BookRepository bookRepository) {
//		this.bookRepository = bookRepository;
//	}
//
//	public Book newBook(String title, String isbn, Integer pageCount, Long authorId) {
//		Book book = new Book();
//		book.setAuthor(new Author(authorId));
//		book.setTitle(title);
//		book.setIsbn(isbn);
//		book.setPageCount(pageCount != null ? pageCount : 0);
//
//		bookRepository.save(book);
//
//		return book;
//	}
//
//	public boolean deleteBook(Long id) {
//		bookRepository.deleteById(id);
//		return true;
//	}
//
//	public Book updateBookPageCount(Integer pageCount, Long id) {
//		Book book = bookRepository.findById(id).get();
//		book.setPageCount(pageCount);
//
//		bookRepository.save(book);
//
//		return book;
//	}
//}
