package ru.sladkov.otus.spring.hw09.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sladkov.otus.spring.hw09.domain.Author;
import ru.sladkov.otus.spring.hw09.domain.Genre;
import ru.sladkov.otus.spring.hw09.dto.BookCreateDto;
import ru.sladkov.otus.spring.hw09.dto.BookDto;
import ru.sladkov.otus.spring.hw09.dto.BookUpdateDto;
import ru.sladkov.otus.spring.hw09.service.AuthorService;
import ru.sladkov.otus.spring.hw09.service.BookService;
import ru.sladkov.otus.spring.hw09.service.GenreService;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping({"/", "/books"})
    public String booksPage(Model model) {
        List<BookDto> books = bookService.getAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/book")
    public String bookDetailsPage(@RequestParam("id") long id, Model model) {
        BookDto bookDto = bookService.getById(id);
        model.addAttribute("book", bookDto);
        return "details";
    }

    @GetMapping("/book/create")
    public String bookCreatePage(Model model) {
        List<Author> authors = authorService.getAll();
        List<Genre> genres = genreService.getAll();

        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "create";
    }

    @PostMapping("/book/create")
    public String createBook(@Valid BookCreateDto bookCreateDto) {
        bookService.create(bookCreateDto);
        return "redirect:/";
    }

    @GetMapping("/book/edit")
    public String bookEditPage(@RequestParam("id") long id, Model model) {
        BookDto bookDto = bookService.getById(id);
        List<Author> authors = authorService.getAll();
        List<Genre> genres = genreService.getAll();

        model.addAttribute("book", bookDto);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "edit";
    }

    @PutMapping("/book/edit")
    public String updateBook(@Valid BookUpdateDto bookUpdateDto) {
        bookService.update(bookUpdateDto);
        return "redirect:/";
    }

    @DeleteMapping("/book/delete")
    public String deleteBook(Long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }

}
