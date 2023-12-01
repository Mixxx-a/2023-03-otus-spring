package ru.sladkov.otus.spring.hw18.service;

import ru.sladkov.otus.spring.hw18.dto.BookCreateDto;
import ru.sladkov.otus.spring.hw18.dto.BookDto;
import ru.sladkov.otus.spring.hw18.dto.BookUpdateDto;

import java.util.List;

public interface BookService {
    BookDto create(BookCreateDto bookCreateDto);

    BookDto getById(long id);

    List<BookDto> getAll();

    BookDto update(BookUpdateDto bookUpdateDto);

    void deleteById(long id);
}
