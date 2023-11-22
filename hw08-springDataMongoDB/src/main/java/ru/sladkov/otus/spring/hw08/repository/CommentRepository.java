package ru.sladkov.otus.spring.hw08.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.sladkov.otus.spring.hw08.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByBookId(String bookId);

    void deleteAllByBookId(String bookId);

}
