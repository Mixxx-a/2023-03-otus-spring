package ru.sladkov.otus.spring.hw16.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sladkov.otus.spring.hw16.dto.BookCreateDto;
import ru.sladkov.otus.spring.hw16.dto.BookDto;
import ru.sladkov.otus.spring.hw16.dto.BookUpdateDto;
import ru.sladkov.otus.spring.hw16.service.BookService;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/api/books/{id}")
    public BookDto getBook(@PathVariable("id") long id) {
        return bookService.getById(id);
    }

    @PostMapping("/api/books")
    public BookDto createBook(@RequestBody @Valid BookCreateDto bookCreateDto) {
        return bookService.create(bookCreateDto);
    }

    @PutMapping("/api/books/{id}")
    public BookDto updateBook(@PathVariable("id") long id, @RequestBody @Valid BookUpdateDto bookUpdateDto) {
        return bookService.update(bookUpdateDto);
    }

    @DeleteMapping("/api/books/{id}")
    public void deleteBook(@PathVariable("id") long id) {
        bookService.deleteById(id);
    }

}
