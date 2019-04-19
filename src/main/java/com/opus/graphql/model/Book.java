package com.opus.graphql.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Entity
public class Book {
    @Id
    private Long id;

    private String title;

    private String isbn;

    private int pageCount;
    
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "author_id",
            nullable = false, updatable = false)
    private Author author;

    public Book() {
    }

    public Book(Long id,String title, String isbn, int pageCount, Author author, Date createdDate) {
    	this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.author = author;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getIsbn(DataFetchingEnvironment environment) {
    	String data = environment.getArgument("data");
    	System.out.println("--->"+data);
    	if(data != null)
    		return isbn.concat("DATA");
    	else 
    		return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", pageCount=" + pageCount +
                ", author=" + author +
                '}';
    }

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
    
//	public Date getCreatedDate(DataFetchingEnvironment environment) {
//        String dateFormat = environment.getArgument("dateFormat");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
////        return simpleDateFormat.format(createdDate);
//        try{
//			return simpleDateFormat.parse(simpleDateFormat.format(createdDate));
//		} catch (ParseException e){
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return createdDate;
//    }
}
