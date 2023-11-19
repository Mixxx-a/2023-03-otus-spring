package ru.sladkov.otus.spring.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sladkov.otus.spring.hw08.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
