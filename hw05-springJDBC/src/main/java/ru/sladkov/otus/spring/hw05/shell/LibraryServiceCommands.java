package ru.sladkov.otus.spring.hw05.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.sladkov.otus.spring.hw05.domain.Author;
import ru.sladkov.otus.spring.hw05.domain.Book;
import ru.sladkov.otus.spring.hw05.domain.Genre;
import ru.sladkov.otus.spring.hw05.service.LibraryService;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class LibraryServiceCommands {

    private final LibraryService libraryService;

    public LibraryServiceCommands(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @ShellMethod(value = "Create book", key = "createBook")
    public String createBook(@ShellOption String id, @ShellOption String title,
                             @ShellOption String authorId, @ShellOption String genreId) {
        boolean result = libraryService.createBook(Long.parseLong(id), title, Long.parseLong(authorId),
                Long.parseLong(genreId));
        return result ? "New book was added!" : "Can't create this book";
    }

    @ShellMethod(value = "Update book", key = "updateBook")
    public String updateBook(@ShellOption String id, @ShellOption String newTitle,
                             @ShellOption String newAuthorId, @ShellOption String newGenreId) {
        boolean result = libraryService.updateBook(Long.parseLong(id), newTitle, Long.parseLong(newAuthorId),
                Long.parseLong(newGenreId));
        return result ? "Book updated successfully!" : "Can't update book";
    }

    @ShellMethod(value = "Get book by id", key = "getBookById")
    public String getBookById(@ShellOption String id) {
        Optional<Book> optionalBook = libraryService.getBook(Long.parseLong(id));
        return createStringFromOptional(optionalBook);
    }

    @ShellMethod(value = "Get all books", key = "getAllBooks")
    public String getAllBooks() {
        List<Book> books = libraryService.getAllBooks();
        return createStringFromList(books);
    }

    @ShellMethod(value = "Delete book", key = "deleteBook")
    public String deleteBook(@ShellOption String id) {
        libraryService.deleteBook(Long.parseLong(id));
        return "Deleted book if it existed";
    }

    @ShellMethod(value = "Get all authors", key = "getAllAuthors")
    public String getAllAuthors() {
        List<Author> authors = libraryService.getAllAuthors();
        return createStringFromList(authors);
    }

    @ShellMethod(value = "Get all genres", key = "getAllGenres")
    public String getAllGenres() {
        List<Genre> genres = libraryService.getAllGenres();
        return createStringFromList(genres);
    }

    @ShellMethod(value = "Get author by id", key = "getAuthorById")
    public String getAuthorById(@ShellOption String id) {
        Optional<Author> optionalAuthor = libraryService.getAuthorById(Long.parseLong(id));
        return createStringFromOptional(optionalAuthor);
    }

    @ShellMethod(value = "Get genre by id", key = "getGenreById")
    public String getGenreById(@ShellOption String id) {
        Optional<Genre> optionalGenre = libraryService.getGenreById(Long.parseLong(id));
        return createStringFromOptional(optionalGenre);
    }

    private String createStringFromOptional(Optional<?> optionalObj) {
        if (optionalObj.isPresent()) {
            return optionalObj.get().toString();
        } else {
            return "Nothing was found!";
        }
    }

    private String createStringFromList(List<?> objects) {
        StringBuilder outputBuilder = new StringBuilder();
        objects.forEach(object -> outputBuilder.append(object.toString()).append("\n"));
        return outputBuilder.toString();
    }
}
