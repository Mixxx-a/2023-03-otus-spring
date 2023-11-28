package ru.sladkov.otus.spring.hw10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw10.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}