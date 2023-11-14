package ru.sladkov.otus.spring.hw07.service;

import ru.sladkov.otus.spring.hw07.domain.Book;
import ru.sladkov.otus.spring.hw07.dto.BookDto;

import java.util.List;

public interface BookService {
    Book create(BookDto bookDto);

    Book getById(long id);

    List<Book> getAll();

    void update(BookDto newBookDto);

    void deleteById(long id);
}
