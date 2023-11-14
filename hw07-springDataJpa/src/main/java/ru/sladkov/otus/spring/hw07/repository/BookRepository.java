package ru.sladkov.otus.spring.hw07.repository;

import ru.sladkov.otus.spring.hw07.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void deleteById(long id);
}
