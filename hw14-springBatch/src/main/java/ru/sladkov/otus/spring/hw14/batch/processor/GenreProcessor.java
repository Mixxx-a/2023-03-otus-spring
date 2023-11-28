package ru.sladkov.otus.spring.hw14.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import ru.sladkov.otus.spring.hw14.domain.jpa.GenreJpa;
import ru.sladkov.otus.spring.hw14.domain.mongo.GenreMongo;

public class GenreProcessor implements ItemProcessor<GenreMongo, GenreJpa> {
    @Override
    public GenreJpa process(GenreMongo genreMongo) {
        return new GenreJpa(null, genreMongo.getName());
    }
}
