package ru.sladkov.otus.spring.hw14.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import ru.sladkov.otus.spring.hw14.domain.jpa.AuthorJpa;
import ru.sladkov.otus.spring.hw14.domain.mongo.AuthorMongo;
import ru.sladkov.otus.spring.hw14.repository.jpa.AuthorJpaRepository;

import java.util.Map;

public class AuthorProcessor implements ItemProcessor<AuthorMongo, AuthorJpa> {

    private final AuthorJpaRepository authorJpaRepository;

    private final Map<String, Long> authorsIdsTransitionMap;

    public AuthorProcessor(AuthorJpaRepository authorJpaRepository, Map<String, Long> authorsIdsTransitionMap) {
        this.authorJpaRepository = authorJpaRepository;
        this.authorsIdsTransitionMap = authorsIdsTransitionMap;
    }

    @Override
    public AuthorJpa process(AuthorMongo authorMongo) {
        Long nextId = authorJpaRepository.getNextId();
        authorsIdsTransitionMap.put(authorMongo.getId(), nextId);
        return new AuthorJpa(nextId, authorMongo.getForename(), authorMongo.getSurname());
    }
}
