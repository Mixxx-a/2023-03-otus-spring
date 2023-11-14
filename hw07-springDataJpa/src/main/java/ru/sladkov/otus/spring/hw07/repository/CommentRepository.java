package ru.sladkov.otus.spring.hw07.repository;

import ru.sladkov.otus.spring.hw07.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAllByBookId(Long bookId);

    void deleteById(long id);
}
