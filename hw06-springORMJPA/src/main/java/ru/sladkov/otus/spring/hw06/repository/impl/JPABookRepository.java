package ru.sladkov.otus.spring.hw06.repository.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;
import ru.sladkov.otus.spring.hw06.domain.Book;
import ru.sladkov.otus.spring.hw06.repository.BookRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JPABookRepository implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    public JPABookRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-entity-graph");
        Map<String, Object> properties = new HashMap<>();
        properties.put(EntityGraphType.FETCH.getKey(), entityGraph);
        return Optional.ofNullable(em.find(Book.class, id, properties));
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-entity-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint(EntityGraphType.FETCH.getKey(), entityGraph);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Book book = em.find(Book.class, id);

        //Delete comments to this book
        Query deleteCommentsQuery = em.createQuery("delete from Comment c where c.book = :book")
                .setParameter("book", book);
        deleteCommentsQuery.executeUpdate();

        em.remove(book);
    }
}
