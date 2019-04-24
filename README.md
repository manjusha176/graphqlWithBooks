# graphqlWithBooks

sample query:

1. Get all books::

POST: http://localhost:8080/query

body: {findAllBooks { id title } }


2. Save Book::

Post: http://localhost:8080/query

body: {saveBook(book:{id:100 title:"book1"}) { id title } }
