package ru.sladkov.otus.spring.hw14.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import ru.sladkov.otus.spring.hw14.domain.jpa.GenreJpa;
import ru.sladkov.otus.spring.hw14.domain.mongo.GenreMongo;
import ru.sladkov.otus.spring.hw14.repository.jpa.GenreJpaRepository;

import java.util.Map;

public class GenreProcessor implements ItemProcessor<GenreMongo, GenreJpa> {

    private final GenreJpaRepository genreJpaRepository;

    private final Map<String, Long> genresIdsTransitionMap;

    public GenreProcessor(GenreJpaRepository genreJpaRepository, Map<String, Long> genresIdsTransitionMap) {
        this.genreJpaRepository = genreJpaRepository;
        this.genresIdsTransitionMap = genresIdsTransitionMap;
    }

    @Override
    public GenreJpa process(GenreMongo genreMongo) {
        Long nextId = genreJpaRepository.getNextId();
        genresIdsTransitionMap.put(genreMongo.getId(), nextId);
        return new GenreJpa(nextId, genreMongo.getName());
    }
}
