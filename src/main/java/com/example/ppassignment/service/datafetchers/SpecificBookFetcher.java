package com.example.ppassignment.service.datafetchers;

import com.example.ppassignment.model.Book;
import com.example.ppassignment.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpecificBookFetcher implements DataFetcher<Book> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
        int bookId = dataFetchingEnvironment.getArgument("book_id");
        return bookRepository.getById(bookId);
    }
}
