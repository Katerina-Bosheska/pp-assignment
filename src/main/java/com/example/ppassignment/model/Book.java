package com.example.ppassignment.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private int id;

    @Column
    private String title;

    @Column
    private Genre genre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "book_id")
    private List<Author> authors;

    public Book(){}

    public Book(String title, Genre genre) {
        this.title = title;
        this.genre = genre;
        this.authors = new ArrayList<>();
    }

    public Book(int id, String title, Genre genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.authors = new ArrayList<>();
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }

        Book b = (Book) o;

        return this.id == b.id
                && this.title.equals(b.title)
                && this.genre == b.genre
                && this.authors.equals(b.authors);
    }
}
