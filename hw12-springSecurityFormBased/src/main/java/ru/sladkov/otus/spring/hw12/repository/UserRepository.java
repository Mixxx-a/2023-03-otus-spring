package ru.sladkov.otus.spring.hw12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sladkov.otus.spring.hw12.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
