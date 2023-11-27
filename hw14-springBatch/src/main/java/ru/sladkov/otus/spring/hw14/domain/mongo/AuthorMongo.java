package ru.sladkov.otus.spring.hw14.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authors")
public class AuthorMongo {
    @Id
    private String id;

    private String forename;

    private String surname;

    public AuthorMongo() {
    }

    public AuthorMongo(String id, String forename, String surname) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
    }

    public AuthorMongo(String forename, String surname) {
        this(null, forename, surname);
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
        return "AuthorMongo[id=" + this.id + ", forename=" + this.forename + ", surname=" + this.surname + "]";
    }
}
