package ru.sladkov.otus.spring.hw05.service;

import ru.sladkov.otus.spring.hw05.domain.Book;
import ru.sladkov.otus.spring.hw05.dto.BookDTO;
import ru.sladkov.otus.spring.hw05.exception.NotFoundException;

import java.util.List;

public interface BookService {
    Book createBook(BookDTO bookDTO) throws NotFoundException;

    Book getBook(long id) throws NotFoundException;

    List<Book> getAllBooks();

    void updateBook(BookDTO newBookDTO) throws NotFoundException;

    void deleteBook(long id);
}
