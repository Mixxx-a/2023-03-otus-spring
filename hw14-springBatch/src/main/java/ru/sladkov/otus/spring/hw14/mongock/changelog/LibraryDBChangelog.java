package ru.sladkov.otus.spring.hw14.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.sladkov.otus.spring.hw14.domain.mongo.AuthorMongo;
import ru.sladkov.otus.spring.hw14.domain.mongo.BookMongo;
import ru.sladkov.otus.spring.hw14.domain.mongo.CommentMongo;
import ru.sladkov.otus.spring.hw14.domain.mongo.GenreMongo;
import ru.sladkov.otus.spring.hw14.repository.mongo.AuthorMongoRepository;
import ru.sladkov.otus.spring.hw14.repository.mongo.BookMongoRepository;
import ru.sladkov.otus.spring.hw14.repository.mongo.CommentMongoRepository;
import ru.sladkov.otus.spring.hw14.repository.mongo.GenreMongoRepository;

import java.util.List;

@ChangeLog
public class LibraryDBChangelog {

    @ChangeSet(order = "001", id = "dropDB", author = "smm", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertData", author = "smm")
    public void insertData(BookMongoRepository bookRepository, AuthorMongoRepository authorRepository,
                           GenreMongoRepository genreRepository, CommentMongoRepository commentRepository) {
        AuthorMongo author1 = new AuthorMongo("Andrzej", "Sapkowski");
        AuthorMongo author2 = new AuthorMongo("Alexander", "Pushkin");
        GenreMongo genre1 = new GenreMongo("Fantasy");
        GenreMongo genre2 = new GenreMongo("Novel");
        BookMongo book1 = new BookMongo("The Witcher", author1, genre1);
        BookMongo book2 = new BookMongo("Eugene Onegin", author2, genre2);
        BookMongo book3 = new BookMongo("Book3", author1, genre2);
        CommentMongo comment1 = new CommentMongo("Very Nice Fantasy called The Witcher!", book1);
        CommentMongo comment2 = new CommentMongo("Cool Novel called Eugene Onegin!", book2);
        CommentMongo comment3 = new CommentMongo("Did not really like it", book1);

        authorRepository.saveAll(List.of(author1, author2));
        genreRepository.saveAll(List.of(genre1, genre2));
        bookRepository.saveAll(List.of(book1, book2, book3));
        commentRepository.saveAll(List.of(comment1, comment2, comment3));
    }

}
