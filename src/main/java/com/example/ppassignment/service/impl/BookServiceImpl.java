package com.example.ppassignment.service.impl;

import com.example.ppassignment.model.Author;
import com.example.ppassignment.model.Book;
import com.example.ppassignment.model.Genre;
import com.example.ppassignment.repository.BookRepository;
import com.example.ppassignment.service.BookService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int bookId) throws NotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new NotFoundException("Book with ID " + bookId + " does not exist.");
        }
        return optionalBook.get();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(int bookId, Book newBook) throws NotFoundException {
        Book book = this.getBookById(bookId);
        book.setTitle(newBook.getTitle());
        book.setGenre(newBook.getGenre());
        book.setAuthors(newBook.getAuthors());
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(int bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent()){
            bookRepository.deleteById(bookId);
        }
    }
}
