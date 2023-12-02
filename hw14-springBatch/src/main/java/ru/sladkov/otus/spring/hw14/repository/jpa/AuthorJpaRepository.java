package ru.sladkov.otus.spring.hw14.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sladkov.otus.spring.hw14.domain.jpa.AuthorJpa;

public interface AuthorJpaRepository extends JpaRepository<AuthorJpa, Long> {

    @Query(value = "SELECT NEXTVAL('AUTHORS_SEQUENCE')", nativeQuery = true)
    Long getNextId();
}
