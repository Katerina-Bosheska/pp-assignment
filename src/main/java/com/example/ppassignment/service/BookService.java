package com.example.ppassignment.service;

import com.example.ppassignment.model.Author;
import com.example.ppassignment.model.Book;
import com.example.ppassignment.model.Genre;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Book> getAllBooks();

    Book getBookById(int bookId) throws NotFoundException;

    Book createBook(Book book);

    Book updateBook(int bookId, Book newBook) throws NotFoundException;

    void deleteBook(int bookId);

}
