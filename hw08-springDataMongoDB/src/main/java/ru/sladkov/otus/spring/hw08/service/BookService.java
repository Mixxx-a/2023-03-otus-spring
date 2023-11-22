package ru.sladkov.otus.spring.hw08.service;

import ru.sladkov.otus.spring.hw08.domain.Book;
import ru.sladkov.otus.spring.hw08.dto.BookDto;

import java.util.List;

public interface BookService {
    Book create(BookDto bookDto);

    Book getById(String id);

    List<Book> getAll();

    void update(BookDto newBookDto);

    void deleteById(String id);
}
