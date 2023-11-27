package ru.sladkov.otus.spring.hw14.repository.jpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw14.domain.jpa.BookJpa;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa extends JpaRepository<BookJpa, Long> {

    @Override
    @EntityGraph(value = "book-entity-graph")
    Optional<BookJpa> findById(Long id);

    @Override
    @EntityGraph(value = "book-entity-graph")
    List<BookJpa> findAll();
}
