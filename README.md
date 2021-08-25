# pp-assignment

The application runs on http://localhost:8080.<br/>
I used H2 database, the necessary configuration for accessing the database can be found in the <i>application.properties</i> file.<br/>
I have implemented few tests to test the web layer and few of the service methods using Mockito.<br/>
The GraphQL queries are directed at the http://localhost:8080/rest endpoint while the other endpoints are exposed on the /rest/books and /rest/authors respectively. I implemented the following request handling methods:<br/>
<pre>GET /rest/books</pre>
returns all books from database
<pre>GET /rest/books/{id}</pre>
gets a specific book
<pre>POST /rest/books/create</pre>
creates new book, a request body (Book object) is required
<pre>PUT /rest/books/update/{id}</pre>
updates an existing book, a request body (Book object, the new book) and the id as path variable are required
<pre>PUT /rest/books/delete/{id}</pre>
deletes specific book
<pre>POST /rest/authors/create</pre>
creates a new author, requires a request body (Author object)
<pre>GET /rest/authors/{id}</pre>
gets a specific author
