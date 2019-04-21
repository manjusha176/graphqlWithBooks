package com.opus.graphql.model;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;

@Entity
@GraphQLName("Author")
public class Author {
	@Id
	@GraphQLField
	private Long id;

	@GraphQLField
	private String firstName;

	@GraphQLField
	private String lastName;

	public Author() {
	}

	public Author(Long id) {
		this.id = id;
	}

	public Author(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Author author = (Author) o;

		return id.equals(author.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return "Author{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
	}
}
