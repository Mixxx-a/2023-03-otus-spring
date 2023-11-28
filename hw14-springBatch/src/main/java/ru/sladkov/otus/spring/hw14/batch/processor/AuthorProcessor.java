package ru.sladkov.otus.spring.hw14.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import ru.sladkov.otus.spring.hw14.domain.jpa.AuthorJpa;
import ru.sladkov.otus.spring.hw14.domain.mongo.AuthorMongo;

public class AuthorProcessor implements ItemProcessor<AuthorMongo, AuthorJpa> {
    @Override
    public AuthorJpa process(AuthorMongo authorMongo) {
        return new AuthorJpa(null, authorMongo.getForename(), authorMongo.getSurname());
    }
}
