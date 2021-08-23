package com.example.ppassignment;

import com.example.ppassignment.model.Author;
import com.example.ppassignment.model.Book;
import com.example.ppassignment.model.Genre;
import com.example.ppassignment.rest.BooksController;
import com.example.ppassignment.rest.InvalidRequestException;
import com.example.ppassignment.service.AuthorService;
import com.example.ppassignment.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BooksController.class)
public class ControllerTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookService bookService;
    @MockBean
    AuthorService authorService;

    private static List<Book> booksList;

    @BeforeAll
    public static void initialize(){

        Book book1 = new Book(1, "Emma", Genre.fiction);
        book1.addAuthor(new Author(1, "Jane", "Austen", null));

        Book book2 = new Book(2, "The Shining", Genre.fiction);
        book2.addAuthor(new Author(2, "Stephen ", "King", null));

        Book book3 = new Book(3, "Dune", Genre.fiction);
        book3.addAuthor(new Author(3, "Frank", "Herbert", null));

        booksList = new ArrayList<>(Arrays.asList(book1, book2, book3));
    }

    @Test
    public void getAllBooks_OK() throws Exception {
        Mockito.when(bookService.getAllBooks()).thenReturn(booksList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3))
        );
    }

    @Test
    public void getBook_OK() throws Exception {
        Mockito.when(bookService.getBookById(1)).thenReturn(booksList.get(0));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(booksList.get(0))));
    }

    @Test
    public void createBook_OK() throws Exception {
        Book book = new Book(4,"Sapiens", Genre.nonfiction);

        Mockito.when(bookService.createBook(Mockito.any(Book.class))).thenReturn(book);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/books/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(book));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(book)));
    }

    @Test
    public void updateBook_Invalid() throws Exception {
        Book book = new Book(4,"Sapiens", Genre.nonfiction);
        Mockito.when(bookService.updateBook(Mockito.any(Integer.class),Mockito.any(Book.class))).thenThrow(InvalidRequestException.class);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/books/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidRequestException))
                .andExpect(result -> assertEquals("Book or Book ID must not be null.", result.getResolvedException().getMessage()));
    }

}
