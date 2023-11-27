package ru.sladkov.otus.spring.hw14.domain.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class AuthorJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "forename")
    private String forename;

    @Column(name = "surname")
    private String surname;

    public AuthorJpa() {
    }

    public AuthorJpa(Long id, String forename, String surname) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
    }

    public Long getId() {
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
        return "AuthorJpa[id=" + this.id + ", forename=" + this.forename + ", surname=" + this.surname + "]";
    }
}
