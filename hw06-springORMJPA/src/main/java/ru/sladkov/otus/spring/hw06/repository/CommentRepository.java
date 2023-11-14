package ru.sladkov.otus.spring.hw06.repository;

import ru.sladkov.otus.spring.hw06.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAllByBookId(Long bookId);

    void deleteById(long id);
}
