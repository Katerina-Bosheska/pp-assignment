package com.example.ppassignment.service;

import com.example.ppassignment.model.Author;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {

    Author createAuthor(Author author);

    Author getAuthorById(int authorId) throws NotFoundException;
}
