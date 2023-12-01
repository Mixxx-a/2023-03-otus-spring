package ru.sladkov.otus.spring.hw14.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import ru.sladkov.otus.spring.hw14.domain.jpa.BookJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.CommentJpa;
import ru.sladkov.otus.spring.hw14.domain.mongo.CommentMongo;
import ru.sladkov.otus.spring.hw14.repository.jpa.BookJpaRepository;

import java.util.Map;

public class CommentProcessor implements ItemProcessor<CommentMongo, CommentJpa> {

    private final BookJpaRepository bookJpaRepository;

    private final Map<String, Long> bookIdsTransitionMap;

    public CommentProcessor(BookJpaRepository bookJpaRepository, Map<String, Long> bookIdsTransitionMap) {
        this.bookJpaRepository = bookJpaRepository;
        this.bookIdsTransitionMap = bookIdsTransitionMap;
    }

    @Override
    public CommentJpa process(CommentMongo commentMongo) {
        Long bookJpaId = bookIdsTransitionMap.get(commentMongo.getBook().getId());
        BookJpa bookJpa = bookJpaRepository.findById(bookJpaId)
                .orElseThrow();

        return new CommentJpa(null, commentMongo.getText(), bookJpa);
    }
}
