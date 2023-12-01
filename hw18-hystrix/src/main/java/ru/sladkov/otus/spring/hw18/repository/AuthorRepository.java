package ru.sladkov.otus.spring.hw18.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw18.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
