package ru.sladkov.otus.spring.hw14.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw14.domain.jpa.AuthorJpa;

public interface AuthorJpaRepository extends JpaRepository<AuthorJpa, Long> {
}
