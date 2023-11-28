package ru.sladkov.otus.spring.hw14.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import ru.sladkov.otus.spring.hw14.domain.jpa.AuthorJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.BookJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.GenreJpa;
import ru.sladkov.otus.spring.hw14.domain.mongo.AuthorMongo;
import ru.sladkov.otus.spring.hw14.domain.mongo.BookMongo;
import ru.sladkov.otus.spring.hw14.repository.jpa.AuthorJpaRepository;
import ru.sladkov.otus.spring.hw14.repository.jpa.GenreJpaRepository;

public class BookProcessor implements ItemProcessor<BookMongo, BookJpa> {

    private final AuthorJpaRepository authorJpaRepository;

    private final GenreJpaRepository genreJpaRepository;

    public BookProcessor(AuthorJpaRepository authorJpaRepository, GenreJpaRepository genreJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
        this.genreJpaRepository = genreJpaRepository;
    }

    @Override
    public BookJpa process(BookMongo bookMongo) {
        AuthorMongo authorMongo = bookMongo.getAuthor();
        AuthorJpa authorJpa = authorJpaRepository.findByForenameAndSurname(authorMongo.getForename(),
                        authorMongo.getSurname())
                .orElseThrow();

        GenreJpa genreJpa = genreJpaRepository.findByName(bookMongo.getGenre().getName())
                .orElseThrow();

        return new BookJpa(null, bookMongo.getTitle(), authorJpa, genreJpa);
    }
}
