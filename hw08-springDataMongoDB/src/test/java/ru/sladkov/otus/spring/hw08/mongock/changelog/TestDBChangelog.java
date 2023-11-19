package ru.sladkov.otus.spring.hw08.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.sladkov.otus.spring.hw08.domain.Author;
import ru.sladkov.otus.spring.hw08.domain.Book;
import ru.sladkov.otus.spring.hw08.domain.Comment;
import ru.sladkov.otus.spring.hw08.domain.Genre;
import ru.sladkov.otus.spring.hw08.repository.AuthorRepository;
import ru.sladkov.otus.spring.hw08.repository.BookRepository;
import ru.sladkov.otus.spring.hw08.repository.CommentRepository;
import ru.sladkov.otus.spring.hw08.repository.GenreRepository;

import java.util.List;

@ChangeLog
public class TestDBChangelog {

    @ChangeSet(order = "001", id = "dropDB", author = "smm", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertData", author = "smm")
    public void insertData(BookRepository bookRepository, AuthorRepository authorRepository,
                           GenreRepository genreRepository, CommentRepository commentRepository) {
        Author author1 = new Author("1", "AuthorForename1", "AuthorSurname1");
        Author author2 = new Author("2", "AuthorForename2", "AuthorSurname2");
        Author author3 = new Author("3", "AuthorForename3", "AuthorSurname3");
        Genre genre1 = new Genre("1", "Genre1");
        Genre genre2 = new Genre("2", "Genre2");
        Genre genre3 = new Genre("3", "Genre3");
        Genre genre4 = new Genre("4", "Genre4");
        Genre genre5 = new Genre("5", "Genre5");
        Book book1 = new Book("1", "Book1", author1, genre1);
        Book book2 = new Book("2", "Book2", author2, genre2);
        Book book3 = new Book("3", "Book3", author1, genre2);
        Comment comment1 = new Comment("1", "Comment1", book1);
        Comment comment2 = new Comment("2", "Comment2", book2);
        Comment comment3 = new Comment("3", "Comment3", book3);
        Comment comment4 = new Comment("4", "Comment4", book1);
        Comment comment5 = new Comment("5", "Comment5", book1);

        authorRepository.saveAll(List.of(author1, author2, author3));
        genreRepository.saveAll(List.of(genre1, genre2, genre3, genre4, genre5));
        bookRepository.saveAll(List.of(book1, book2, book3));
        commentRepository.saveAll(List.of(comment1, comment2, comment3, comment4, comment5));
    }

}
