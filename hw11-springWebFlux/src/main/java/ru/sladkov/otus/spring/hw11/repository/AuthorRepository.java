package ru.sladkov.otus.spring.hw11.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sladkov.otus.spring.hw11.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
