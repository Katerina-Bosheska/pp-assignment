package com.example.ppassignment.service.datafetchers;

import com.example.ppassignment.model.Author;
import com.example.ppassignment.repository.AuthorRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SpecificAuthorFetcher implements DataFetcher<Author> {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author get(DataFetchingEnvironment dataFetchingEnvironment) {
        int authorId = dataFetchingEnvironment.getArgument("author_id");
        return authorRepository.getById(authorId);
    }
}
