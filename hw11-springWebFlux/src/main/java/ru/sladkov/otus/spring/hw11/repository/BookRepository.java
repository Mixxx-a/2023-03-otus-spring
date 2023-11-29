package ru.sladkov.otus.spring.hw11.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.sladkov.otus.spring.hw11.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

}
