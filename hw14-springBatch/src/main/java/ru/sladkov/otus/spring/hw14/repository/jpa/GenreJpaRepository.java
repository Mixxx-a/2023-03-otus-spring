package ru.sladkov.otus.spring.hw14.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw14.domain.jpa.GenreJpa;

import java.util.Optional;

public interface GenreJpaRepository extends JpaRepository<GenreJpa, Long> {

    Optional<GenreJpa> findByName(String name);
}
