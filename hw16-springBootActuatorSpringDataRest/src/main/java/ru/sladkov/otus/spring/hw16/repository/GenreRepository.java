package ru.sladkov.otus.spring.hw16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw16.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
