package ru.sladkov.otus.spring.hw14.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sladkov.otus.spring.hw14.domain.mongo.GenreMongo;

public interface GenreRepositoryMongo extends MongoRepository<GenreMongo, String> {

}
