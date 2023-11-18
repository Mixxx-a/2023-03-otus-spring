package ru.sladkov.otus.spring.hw07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw07.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
