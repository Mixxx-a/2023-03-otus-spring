package ru.sladkov.otus.spring.hw09.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.sladkov.otus.spring.hw09.domain.Author;
import ru.sladkov.otus.spring.hw09.domain.Book;
import ru.sladkov.otus.spring.hw09.domain.Comment;
import ru.sladkov.otus.spring.hw09.domain.Genre;
import ru.sladkov.otus.spring.hw09.dto.BookDto;
import ru.sladkov.otus.spring.hw09.dto.CommentDto;
import ru.sladkov.otus.spring.hw09.service.AuthorService;
import ru.sladkov.otus.spring.hw09.service.BookService;
import ru.sladkov.otus.spring.hw09.service.CommentService;
import ru.sladkov.otus.spring.hw09.service.GenreService;

import java.util.List;

@ShellComponent
public class LibraryServiceCommands {

    private final AuthorService authorService;

    private final BookService bookService;

    private final GenreService genreService;

    private final CommentService commentService;

    public LibraryServiceCommands(AuthorService authorService, BookService bookService, GenreService genreService,
                                  CommentService commentService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.genreService = genreService;
        this.commentService = commentService;
    }

    @ShellMethod(value = "Create book", key = "createBook")
    public String createBook(@ShellOption String title, @ShellOption String authorId, @ShellOption String genreId) {
        BookDto bookDto = new BookDto(title, Long.parseLong(authorId), Long.parseLong(genreId));
        Book createdBook = bookService.create(bookDto);
        return "New book created with id " + createdBook.getId();
    }

    @ShellMethod(value = "Update book", key = "updateBook")
    public String updateBook(@ShellOption String id, @ShellOption String newTitle,
                             @ShellOption String newAuthorId, @ShellOption String newGenreId) {
        BookDto newBookDto = new BookDto(Long.parseLong(id), newTitle, Long.parseLong(newAuthorId),
                Long.parseLong(newGenreId));
        bookService.update(newBookDto);
        return "Book updated successfully!";
    }

    @ShellMethod(value = "Get book by id", key = "getBookById")
    public String getBookById(@ShellOption String id) {
        Book book = bookService.getById(Long.parseLong(id));
        return book.toString();
    }

    @ShellMethod(value = "Get all books", key = "getAllBooks")
    public String getAllBooks() {
        List<Book> books = bookService.getAll();
        return createStringFromList(books);
    }

    @ShellMethod(value = "Delete book", key = "deleteBook")
    public String deleteBook(@ShellOption String id) {
        bookService.deleteById(Long.parseLong(id));
        return "Deleted book if it existed";
    }

    @ShellMethod(value = "Get author by id", key = "getAuthorById")
    public String getAuthorById(@ShellOption String id) {
        Author author = authorService.getById(Long.parseLong(id));
        return author.toString();
    }

    @ShellMethod(value = "Get all authors", key = "getAllAuthors")
    public String getAllAuthors() {
        List<Author> authors = authorService.getAll();
        return createStringFromList(authors);
    }

    @ShellMethod(value = "Get genre by id", key = "getGenreById")
    public String getGenreById(@ShellOption String id) {
        Genre genre = genreService.getById(Long.parseLong(id));
        return genre.toString();
    }

    @ShellMethod(value = "Get all genres", key = "getAllGenres")
    public String getAllGenres() {
        List<Genre> genres = genreService.getAll();
        return createStringFromList(genres);
    }

    @ShellMethod(value = "Create comment", key = "createComment")
    public String createComment(@ShellOption String text, @ShellOption String bookId) {
        CommentDto commentDTO = new CommentDto(text, Long.parseLong(bookId));
        Comment createdComment = commentService.create(commentDTO);
        return "New comment created with id " + createdComment.getId();
    }

    @ShellMethod(value = "Update comment", key = "updateComment")
    public String updateComment(@ShellOption String id, @ShellOption String newText, @ShellOption String newBookId) {
        CommentDto newCommentDto = new CommentDto(Long.parseLong(id), newText, Long.parseLong(newBookId));
        commentService.update(newCommentDto);
        return "Comment updated successfully!";
    }

    @ShellMethod(value = "Get comment by id", key = "getCommentById")
    public String getCommentById(@ShellOption String id) {
        Comment comment = commentService.getById(Long.parseLong(id));
        return comment.toString();
    }

    @ShellMethod(value = "Get all comments by book id", key = "getAllCommentsByBookId")
    public String getAllCommentsByBookId(@ShellOption String bookId) {
        List<Comment> comments = commentService.getAllByBookId(Long.parseLong(bookId));
        return createStringFromList(comments);
    }

    @ShellMethod(value = "Delete comment", key = "deleteComment")
    public String deleteComment(@ShellOption String id) {
        commentService.deleteById(Long.parseLong(id));
        return "Deleted comment if it existed";
    }

    private String createStringFromList(List<?> objects) {
        StringBuilder outputBuilder = new StringBuilder();
        objects.forEach(object -> outputBuilder.append(object.toString())
                .append("\n"));
        return outputBuilder.toString();
    }
}
