package com.example.ppassignment.service.datafetchers;

import com.example.ppassignment.model.Author;
import com.example.ppassignment.repository.AuthorRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllAuthorsDataFetcher implements DataFetcher<List<Author>> {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return authorRepository.findAll();
    }
}
