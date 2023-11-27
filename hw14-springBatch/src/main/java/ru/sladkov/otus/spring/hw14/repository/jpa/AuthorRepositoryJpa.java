package ru.sladkov.otus.spring.hw14.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw14.domain.jpa.AuthorJpa;

public interface AuthorRepositoryJpa extends JpaRepository<AuthorJpa, Long> {

}
