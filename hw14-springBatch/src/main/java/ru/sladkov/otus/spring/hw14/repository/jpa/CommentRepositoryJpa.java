package ru.sladkov.otus.spring.hw14.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw14.domain.jpa.CommentJpa;

import java.util.List;

public interface CommentRepositoryJpa extends JpaRepository<CommentJpa, Long> {

    List<CommentJpa> findByBookId(Long bookId);

    void deleteAllByBookId(Long bookId);

}
