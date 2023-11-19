package ru.sladkov.otus.spring.hw08.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authors")
public class Author {
    @Id
    private String id;

    private String forename;

    private String surname;

    public Author() {
    }

    public Author(String id, String forename, String surname) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
    }

    public Author(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Author[id=" + this.id + ", forename=" + this.forename + ", surname=" + this.surname + "]";
    }
}
