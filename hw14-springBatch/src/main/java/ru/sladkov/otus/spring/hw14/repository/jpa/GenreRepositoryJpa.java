package ru.sladkov.otus.spring.hw14.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw14.domain.jpa.GenreJpa;

public interface GenreRepositoryJpa extends JpaRepository<GenreJpa, Long> {

}
