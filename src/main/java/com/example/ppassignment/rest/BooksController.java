package com.example.ppassignment.rest;

import com.example.ppassignment.model.Author;
import com.example.ppassignment.model.Book;
import com.example.ppassignment.service.AuthorService;
import com.example.ppassignment.service.BookService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {

    private final AuthorService authorService;
    private final BookService bookService;

    public BooksController(AuthorService authorService, BookService bookService){
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @PostMapping("/books/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        if(book == null)
            throw new InvalidRequestException("Book must not be null.");
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.OK);
    }

    @PutMapping("/books/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id,
                           @RequestBody(required = false) Book book) throws NotFoundException {
        if(id == null || book == null)
            throw new InvalidRequestException("Book or Book ID must not be null.");
        return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK);
    }

    @PutMapping("/books/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable int id){
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/authors/create")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        if(author == null)
            throw new InvalidRequestException("Author must not be null.");
        return new ResponseEntity<>(authorService.createAuthor(author), HttpStatus.OK);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }


}
