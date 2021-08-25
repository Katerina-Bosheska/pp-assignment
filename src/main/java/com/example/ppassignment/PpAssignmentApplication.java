package com.example.ppassignment;

import com.example.ppassignment.model.Author;
import com.example.ppassignment.model.Book;
import com.example.ppassignment.model.Genre;
import com.example.ppassignment.repository.AuthorRepository;
import com.example.ppassignment.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

@SpringBootApplication
public class PpAssignmentApplication {

    public static void loadData(BookRepository bookRepository) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = formatter.parse("1920-05-16");

        Book book1 = new Book( "Emma", Genre.nonfiction);
        book1.addAuthor(new Author("Jane", "Austen", date));

        Book book2 = new Book("The Shining", Genre.fiction);
        book2.addAuthor(new Author("Stephen ", "King", date));

        Book book3 = new Book("Dune", Genre.fiction);
        book3.setAuthors(Arrays.asList(new Author("Frank", "Herbert", date),
                                        new Author("Franklin", "Patrick", date)));

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }

    public static void main(String[] args) throws ParseException {

        //SpringApplication.run(PpAssignmentApplication.class, args);

        ApplicationContext applicationContext = SpringApplication.run(PpAssignmentApplication.class, args);

        BookRepository bookRepository = applicationContext.getBean(BookRepository.class);

        loadData(bookRepository);
    }

}
