package ru.sladkov.otus.spring.hw05.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.sladkov.otus.spring.hw05.domain.Author;
import ru.sladkov.otus.spring.hw05.domain.Book;
import ru.sladkov.otus.spring.hw05.domain.Genre;
import ru.sladkov.otus.spring.hw05.dto.BookDto;
import ru.sladkov.otus.spring.hw05.service.AuthorService;
import ru.sladkov.otus.spring.hw05.service.BookService;
import ru.sladkov.otus.spring.hw05.service.GenreService;

import java.util.List;

@ShellComponent
public class LibraryServiceCommands {

    private final AuthorService authorService;

    private final BookService bookService;

    private final GenreService genreService;

    public LibraryServiceCommands(AuthorService authorService, BookService bookService, GenreService genreService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.genreService = genreService;
    }

    @ShellMethod(value = "Create book", key = "createBook")
    public String createBook(@ShellOption String title, @ShellOption String authorId, @ShellOption String genreId) {
        BookDto bookDto = new BookDto(title, Long.parseLong(authorId), Long.parseLong(genreId));
        Book createdBook = bookService.createBook(bookDto);
        return "New book created with id " + createdBook.id();
    }

    @ShellMethod(value = "Update book", key = "updateBook")
    public String updateBook(@ShellOption String id, @ShellOption String newTitle,
                             @ShellOption String newAuthorId, @ShellOption String newGenreId) {
        BookDto newBookDto = new BookDto(Long.parseLong(id), newTitle, Long.parseLong(newAuthorId),
                Long.parseLong(newGenreId));
        bookService.updateBook(newBookDto);
        return "Book updated successfully!";
    }

    @ShellMethod(value = "Get book by id", key = "getBookById")
    public String getBookById(@ShellOption String id) {
        Book book = bookService.getBook(Long.parseLong(id));
        return book.toString();
    }

    @ShellMethod(value = "Get all books", key = "getAllBooks")
    public String getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return createStringFromList(books);
    }

    @ShellMethod(value = "Delete book", key = "deleteBook")
    public String deleteBook(@ShellOption String id) {
        bookService.deleteBook(Long.parseLong(id));
        return "Deleted book if it existed";
    }

    @ShellMethod(value = "Get author by id", key = "getAuthorById")
    public String getAuthorById(@ShellOption String id) {
        Author author = authorService.getAuthorById(Long.parseLong(id));
        return author.toString();
    }

    @ShellMethod(value = "Get all authors", key = "getAllAuthors")
    public String getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return createStringFromList(authors);
    }

    @ShellMethod(value = "Get genre by id", key = "getGenreById")
    public String getGenreById(@ShellOption String id) {
        Genre genre = genreService.getGenreById(Long.parseLong(id));
        return genre.toString();
    }

    @ShellMethod(value = "Get all genres", key = "getAllGenres")
    public String getAllGenres() {
        List<Genre> genres = genreService.getAllGenres();
        return createStringFromList(genres);
    }

    private String createStringFromList(List<?> objects) {
        StringBuilder outputBuilder = new StringBuilder();
        objects.forEach(object -> outputBuilder.append(object.toString())
                .append("\n"));
        return outputBuilder.toString();
    }
}
