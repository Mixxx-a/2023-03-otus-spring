package ru.sladkov.otus.spring.hw12.service;

import ru.sladkov.otus.spring.hw12.dto.BookCreateDto;
import ru.sladkov.otus.spring.hw12.dto.BookDto;
import ru.sladkov.otus.spring.hw12.dto.BookUpdateDto;

import java.util.List;

public interface BookService {
    BookDto create(BookCreateDto bookCreateDto);

    BookDto getById(long id);

    List<BookDto> getAll();

    BookDto update(BookUpdateDto bookUpdateDto);

    void deleteById(long id);
}
