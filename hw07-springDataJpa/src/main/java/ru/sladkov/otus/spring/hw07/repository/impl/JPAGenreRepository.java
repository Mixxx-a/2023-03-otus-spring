package ru.sladkov.otus.spring.hw07.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.sladkov.otus.spring.hw07.domain.Genre;
import ru.sladkov.otus.spring.hw07.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class JPAGenreRepository implements GenreRepository {

    @PersistenceContext
    private final EntityManager em;

    public JPAGenreRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery("select g from Genre g", Genre.class)
                .getResultList();
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }
}
