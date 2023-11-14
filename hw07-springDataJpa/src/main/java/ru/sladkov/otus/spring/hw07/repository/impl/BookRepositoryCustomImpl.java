package ru.sladkov.otus.spring.hw07.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import ru.sladkov.otus.spring.hw07.domain.Book;
import ru.sladkov.otus.spring.hw07.repository.BookRepositoryCustom;

@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @PersistenceContext
    private final EntityManager em;

    public BookRepositoryCustomImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void deleteByIdWithComments(long id) {
        Book book = em.find(Book.class, id);

        //Delete comments to this book
        Query deleteCommentsQuery = em.createQuery("delete from Comment c where c.book = :book")
                .setParameter("book", book);
        deleteCommentsQuery.executeUpdate();

        em.remove(book);
    }
}
