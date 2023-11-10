package ru.sladkov.otus.spring.hw06.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.sladkov.otus.spring.hw06.domain.Book;
import ru.sladkov.otus.spring.hw06.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJPA implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    public BookRepositoryJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null || book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b " +
                        "join fetch b.author " +
                        "join fetch b.genre ",
                Book.class);
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
