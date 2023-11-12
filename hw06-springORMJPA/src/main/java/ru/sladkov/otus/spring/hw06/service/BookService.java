package ru.sladkov.otus.spring.hw06.service;

import ru.sladkov.otus.spring.hw06.domain.Book;
import ru.sladkov.otus.spring.hw06.dto.BookDto;

import java.util.List;

public interface BookService {
    Book create(BookDto bookDto);

    Book getById(long id);

    List<Book> getAll();

    void update(BookDto newBookDto);

    void deleteById(long id);
}
