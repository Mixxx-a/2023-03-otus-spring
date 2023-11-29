package ru.sladkov.otus.spring.hw11.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sladkov.otus.spring.hw11.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
