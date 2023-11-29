package ru.sladkov.otus.spring.hw11.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sladkov.otus.spring.hw11.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {

}
