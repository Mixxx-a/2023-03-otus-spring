package ru.sladkov.otus.spring.hw05.service;

import ru.sladkov.otus.spring.hw05.domain.Book;
import ru.sladkov.otus.spring.hw05.dto.BookDto;

import java.util.List;

public interface BookService {
    Book createBook(BookDto bookDto);

    Book getBook(long id);

    List<Book> getAllBooks();

    void updateBook(BookDto newBookDto);

    void deleteBook(long id);
}
