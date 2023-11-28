package ru.sladkov.otus.spring.hw14.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw14.domain.jpa.AuthorJpa;

import java.util.Optional;

public interface AuthorJpaRepository extends JpaRepository<AuthorJpa, Long> {

    Optional<AuthorJpa> findByForenameAndSurname(String forename, String surname);
}
