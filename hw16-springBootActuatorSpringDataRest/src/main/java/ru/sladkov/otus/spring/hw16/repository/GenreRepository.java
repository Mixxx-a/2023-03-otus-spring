package ru.sladkov.otus.spring.hw16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.sladkov.otus.spring.hw16.domain.Genre;

@RepositoryRestResource(path = "genre")
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
