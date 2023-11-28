package ru.sladkov.otus.spring.hw14.batch.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sladkov.otus.spring.hw14.batch.processor.AuthorProcessor;
import ru.sladkov.otus.spring.hw14.batch.processor.BookProcessor;
import ru.sladkov.otus.spring.hw14.batch.processor.CommentProcessor;
import ru.sladkov.otus.spring.hw14.batch.processor.GenreProcessor;
import ru.sladkov.otus.spring.hw14.domain.jpa.AuthorJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.BookJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.CommentJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.GenreJpa;
import ru.sladkov.otus.spring.hw14.domain.mongo.AuthorMongo;
import ru.sladkov.otus.spring.hw14.domain.mongo.BookMongo;
import ru.sladkov.otus.spring.hw14.domain.mongo.CommentMongo;
import ru.sladkov.otus.spring.hw14.domain.mongo.GenreMongo;
import ru.sladkov.otus.spring.hw14.repository.jpa.AuthorJpaRepository;
import ru.sladkov.otus.spring.hw14.repository.jpa.BookJpaRepository;
import ru.sladkov.otus.spring.hw14.repository.jpa.GenreJpaRepository;

@SuppressWarnings("unused")
@Configuration
public class ItemProcessorConfig {

    private final AuthorJpaRepository authorJpaRepository;

    private final GenreJpaRepository genreJpaRepository;

    private final BookJpaRepository bookJpaRepository;

    public ItemProcessorConfig(AuthorJpaRepository authorJpaRepository, GenreJpaRepository genreJpaRepository,
                               BookJpaRepository bookJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
        this.genreJpaRepository = genreJpaRepository;
        this.bookJpaRepository = bookJpaRepository;
    }

    @Bean
    public ItemProcessor<AuthorMongo, AuthorJpa> authorProcessor() {
        return new AuthorProcessor();
    }

    @Bean
    public ItemProcessor<GenreMongo, GenreJpa> genreProcessor() {
        return new GenreProcessor();
    }

    @Bean
    public ItemProcessor<BookMongo, BookJpa> bookProcessor() {
        return new BookProcessor(authorJpaRepository, genreJpaRepository);
    }

    @Bean
    public ItemProcessor<CommentMongo, CommentJpa> commentProcessor() {
        return new CommentProcessor(bookJpaRepository);
    }
}