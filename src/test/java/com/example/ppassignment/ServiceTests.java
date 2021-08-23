package com.example.ppassignment;

import com.example.ppassignment.model.Book;
import com.example.ppassignment.model.Genre;
import com.example.ppassignment.repository.BookRepository;
import com.example.ppassignment.rest.InvalidRequestException;
import com.example.ppassignment.service.BookService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class ServiceTests {

    @Autowired
    private BookService bookService;

    @MockBean
    BookRepository bookRepository;

    @Test
    public void getBook_Present() throws NotFoundException {
        Book book = new Book(1, "Emma", Genre.fiction);
        Optional<Book> optionalBook = Optional.of(new Book(1, "Emma", Genre.fiction));

        Mockito.when(bookRepository.findById(1)).thenReturn(optionalBook);

        Assertions.assertEquals(book, bookService.getBookById(1));
    }

    @Test
    public void getBook_Empty() throws NotFoundException {
        Mockito.when(bookRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> bookService.getBookById(5));
    }

}
