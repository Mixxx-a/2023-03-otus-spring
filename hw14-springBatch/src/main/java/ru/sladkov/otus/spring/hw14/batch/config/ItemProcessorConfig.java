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

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@Configuration
public class ItemProcessorConfig {

    private final AuthorJpaRepository authorJpaRepository;

    private final GenreJpaRepository genreJpaRepository;

    private final BookJpaRepository bookJpaRepository;

    private final Map<String, Long> authorsIdsTransitionMap = new HashMap<>();

    private final Map<String, Long> genresIdsTransitionMap = new HashMap<>();

    private final Map<String, Long> bookIdsTransitionMap = new HashMap<>();

    public ItemProcessorConfig(AuthorJpaRepository authorJpaRepository, GenreJpaRepository genreJpaRepository,
                               BookJpaRepository bookJpaRepository) {
        this.authorJpaRepository = authorJpaRepository;
        this.genreJpaRepository = genreJpaRepository;
        this.bookJpaRepository = bookJpaRepository;
    }

    @Bean
    public ItemProcessor<AuthorMongo, AuthorJpa> authorProcessor() {
        return new AuthorProcessor(authorJpaRepository, authorsIdsTransitionMap);
    }

    @Bean
    public ItemProcessor<GenreMongo, GenreJpa> genreProcessor() {
        return new GenreProcessor(genreJpaRepository, genresIdsTransitionMap);
    }

    @Bean
    public ItemProcessor<BookMongo, BookJpa> bookProcessor() {
        return new BookProcessor.BookProcessorBuilder()
                .setBookJpaRepository(bookJpaRepository)
                .setAuthorJpaRepository(authorJpaRepository)
                .setGenreJpaRepository(genreJpaRepository)
                .setBookIdsTransitionMap(bookIdsTransitionMap)
                .setAuthorsIdsTransitionMap(authorsIdsTransitionMap)
                .setGenresIdsTransitionMap(genresIdsTransitionMap)
                .build();
    }

    @Bean
    public ItemProcessor<CommentMongo, CommentJpa> commentProcessor() {
        return new CommentProcessor(bookJpaRepository, bookIdsTransitionMap);
    }
}