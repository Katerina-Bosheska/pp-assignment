package com.example.ppassignment.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "author_id")
    private int id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private Date birthdate;

    public Author(){}

    public Author(String name, String surname, Date birthdate){
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public Author(int id, String name, String surname, Date birthdate){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof Author)) {
            return false;
        }

        Author a = (Author) o;

        return this.id == a.id
                && this.name.equals(a.name)
                && this.surname.equals(a.surname)
                && this.birthdate.equals(a.birthdate);
    }
}
