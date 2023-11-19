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
public class LibraryDBChangelog {

    @ChangeSet(order = "001", id = "dropDB", author = "smm", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertData", author = "smm")
    public void insertData(BookRepository bookRepository, AuthorRepository authorRepository,
                           GenreRepository genreRepository, CommentRepository commentRepository) {
        Author author1 = new Author("Andrzej", "Sapkowski");
        Author author2 = new Author("Alexander", "Pushkin");
        Genre genre1 = new Genre("Fantasy");
        Genre genre2 = new Genre("Novel");
        Book book1 = new Book("The Witcher", author1, genre1);
        Book book2 = new Book("Eugene Onegin", author2, genre2);
        Book book3 = new Book("Book3", author1, genre2);
        Comment comment1 = new Comment("Very Nice Fantasy called The Witcher!", book1);
        Comment comment2 = new Comment("Cool Novel called Eugene Onegin!", book2);
        Comment comment3 = new Comment("Did not really like it", book1);

        authorRepository.saveAll(List.of(author1, author2));
        genreRepository.saveAll(List.of(genre1, genre2));
        bookRepository.saveAll(List.of(book1, book2, book3));
        commentRepository.saveAll(List.of(comment1, comment2, comment3));
    }

}
