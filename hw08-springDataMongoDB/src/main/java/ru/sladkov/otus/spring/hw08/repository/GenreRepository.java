package ru.sladkov.otus.spring.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sladkov.otus.spring.hw08.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
