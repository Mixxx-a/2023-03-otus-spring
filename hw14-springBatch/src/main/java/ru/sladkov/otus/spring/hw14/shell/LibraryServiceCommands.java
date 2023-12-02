package ru.sladkov.otus.spring.hw14.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.sladkov.otus.spring.hw14.domain.jpa.AuthorJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.BookJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.CommentJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.GenreJpa;
import ru.sladkov.otus.spring.hw14.repository.jpa.AuthorJpaRepository;
import ru.sladkov.otus.spring.hw14.repository.jpa.BookJpaRepository;
import ru.sladkov.otus.spring.hw14.repository.jpa.CommentJpaRepository;
import ru.sladkov.otus.spring.hw14.repository.jpa.GenreJpaRepository;

import java.util.List;

@SuppressWarnings("unused")
@ShellComponent
public class LibraryServiceCommands {

    private final AuthorJpaRepository authorJpaRepository;

    private final BookJpaRepository bookJpaRepository;

    private final GenreJpaRepository genreJpaRepository;

    private final CommentJpaRepository commentJpaRepository;

    public LibraryServiceCommands(AuthorJpaRepository authorJpaRepository, BookJpaRepository bookJpaRepository,
                                  GenreJpaRepository genreJpaRepository, CommentJpaRepository commentJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
        this.genreJpaRepository = genreJpaRepository;
        this.bookJpaRepository = bookJpaRepository;
        this.commentJpaRepository = commentJpaRepository;
    }

    @ShellMethod(value = "Get all books", key = "getAllBooks")
    public String getAllBooks() {
        List<BookJpa> books = bookJpaRepository.findAll();
        return createStringFromList(books);
    }

    @ShellMethod(value = "Get all authors", key = "getAllAuthors")
    public String getAllAuthors() {
        List<AuthorJpa> authors = authorJpaRepository.findAll();
        return createStringFromList(authors);
    }

    @ShellMethod(value = "Get all genres", key = "getAllGenres")
    public String getAllGenres() {
        List<GenreJpa> genres = genreJpaRepository.findAll();
        return createStringFromList(genres);
    }


    @ShellMethod(value = "Get all comments", key = "getAllComments")
    public String getAllComments() {
        List<CommentJpa> comments = commentJpaRepository.findAll();
        return createStringFromList(comments);
    }

    private String createStringFromList(List<?> objects) {
        StringBuilder outputBuilder = new StringBuilder();
        objects.forEach(object -> outputBuilder.append(object.toString())
                .append("\n"));
        return outputBuilder.toString();
    }
}
