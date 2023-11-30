package ru.sladkov.otus.spring.hw13.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw13.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(value = "user-entity-graph")
    Optional<User> findByUsername(String username);
}
