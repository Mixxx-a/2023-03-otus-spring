package ru.sladkov.otus.spring.hw14.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sladkov.otus.spring.hw14.domain.jpa.GenreJpa;

public interface GenreJpaRepository extends JpaRepository<GenreJpa, Long> {

    @Query(value = "SELECT NEXTVAL('GENRES_SEQUENCE')", nativeQuery = true)
    Long getNextId();
}
