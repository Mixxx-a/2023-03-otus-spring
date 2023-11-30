package ru.sladkov.otus.spring.hw17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw17.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
