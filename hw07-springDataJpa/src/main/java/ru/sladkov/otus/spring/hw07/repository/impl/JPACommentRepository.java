package ru.sladkov.otus.spring.hw07.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.sladkov.otus.spring.hw07.domain.Book;
import ru.sladkov.otus.spring.hw07.domain.Comment;
import ru.sladkov.otus.spring.hw07.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class JPACommentRepository implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    public JPACommentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAllByBookId(Long bookId) {
        Book book = em.find(Book.class, bookId);
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book = :book", Comment.class)
                .setParameter("book", book);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
    }
}
