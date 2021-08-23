package com.example.ppassignment.service.impl;

import com.example.ppassignment.model.Author;
import com.example.ppassignment.model.Book;
import com.example.ppassignment.repository.AuthorRepository;
import com.example.ppassignment.service.AuthorService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author getAuthorById(int authorId) throws NotFoundException {
        Optional<Author> optionalBook = authorRepository.findById(authorId);
        if(optionalBook.isEmpty()){
            throw new NotFoundException("Author with ID " + authorId + " does not exist.");
        }
        return authorRepository.getById(authorId);
    }
}
