package ru.sladkov.otus.spring.hw05.dao;

import ru.sladkov.otus.spring.hw05.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book create(Book book);

    void update(Book book);

    Optional<Book> getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
