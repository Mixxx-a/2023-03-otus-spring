package ru.sladkov.otus.spring.hw11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.sladkov.otus.spring.hw11.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}
