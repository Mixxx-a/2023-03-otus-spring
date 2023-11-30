package ru.sladkov.otus.spring.hw16.repository;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.sladkov.otus.spring.hw16.domain.Book;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "book")
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @EntityGraph(value = "book-entity-graph")
    @Nonnull
    Optional<Book> findById(@Nonnull Long id);

    @Override
    @EntityGraph(value = "book-entity-graph")
    @Nonnull
    List<Book> findAll();
}
