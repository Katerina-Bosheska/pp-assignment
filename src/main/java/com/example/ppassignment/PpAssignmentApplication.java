package com.example.ppassignment;

import com.example.ppassignment.model.Author;
import com.example.ppassignment.model.Book;
import com.example.ppassignment.model.Genre;
import com.example.ppassignment.repository.AuthorRepository;
import com.example.ppassignment.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class PpAssignmentApplication {

    public static void loadData(BookRepository bookRepository){
        Book book1 = new Book( "Emma", Genre.nonfiction);
        book1.addAuthor(new Author("Jane", "Austen", null));

        Book book2 = new Book("The Shining", Genre.fiction);
        book2.addAuthor(new Author("Stephen ", "King", null));

        Book book3 = new Book("Dune", Genre.fiction);
        book3.setAuthors(Arrays.asList(new Author("Frank", "Herbert", null),
                                        new Author("Franklin", "Patrick", null)));

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }

    public static void main(String[] args) {

        //SpringApplication.run(PpAssignmentApplication.class, args);

        ApplicationContext applicationContext = SpringApplication.run(PpAssignmentApplication.class, args);

        BookRepository bookRepository = applicationContext.getBean(BookRepository.class);

        loadData(bookRepository);
    }

}
