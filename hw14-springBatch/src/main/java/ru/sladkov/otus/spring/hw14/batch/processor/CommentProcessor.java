package ru.sladkov.otus.spring.hw14.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import ru.sladkov.otus.spring.hw14.domain.jpa.BookJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.CommentJpa;
import ru.sladkov.otus.spring.hw14.domain.mongo.CommentMongo;
import ru.sladkov.otus.spring.hw14.repository.jpa.BookJpaRepository;

public class CommentProcessor implements ItemProcessor<CommentMongo, CommentJpa> {

    private final BookJpaRepository bookJpaRepository;

    public CommentProcessor(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public CommentJpa process(CommentMongo commentMongo) {
        BookJpa bookJpa = bookJpaRepository.findByTitle(commentMongo.getBook().getTitle())
                .orElseThrow();

        return new CommentJpa(null, commentMongo.getText(), bookJpa);
    }
}
