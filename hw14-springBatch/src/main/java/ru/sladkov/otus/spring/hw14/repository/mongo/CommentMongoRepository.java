package ru.sladkov.otus.spring.hw14.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sladkov.otus.spring.hw14.domain.mongo.CommentMongo;

import java.util.List;

public interface CommentMongoRepository extends MongoRepository<CommentMongo, String> {

    List<CommentMongo> findByBookId(String bookId);

    void deleteAllByBookId(String bookId);

}
