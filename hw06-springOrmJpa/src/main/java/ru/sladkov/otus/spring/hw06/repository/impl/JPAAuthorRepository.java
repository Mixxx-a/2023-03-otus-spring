package ru.sladkov.otus.spring.hw06.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.sladkov.otus.spring.hw06.domain.Author;
import ru.sladkov.otus.spring.hw06.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class JPAAuthorRepository implements AuthorRepository {

    @PersistenceContext
    private final EntityManager em;

    public JPAAuthorRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Author> findAll() {
        return em.createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }
}
