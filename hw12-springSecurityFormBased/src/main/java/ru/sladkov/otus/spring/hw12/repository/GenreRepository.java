package ru.sladkov.otus.spring.hw12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw12.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
