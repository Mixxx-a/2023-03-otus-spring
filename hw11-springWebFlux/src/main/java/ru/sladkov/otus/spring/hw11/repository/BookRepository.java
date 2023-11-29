package ru.sladkov.otus.spring.hw11.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw11.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @EntityGraph(value = "book-entity-graph")
    Optional<Book> findById(Long id);

    @Override
    @EntityGraph(value = "book-entity-graph")
    List<Book> findAll();
}
