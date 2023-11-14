package ru.sladkov.otus.spring.hw07.repository.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import ru.sladkov.otus.spring.hw07.repository.BookRepository;
import ru.sladkov.otus.spring.hw07.repository.BookRepositoryCustom;
import ru.sladkov.otus.spring.hw07.repository.CommentRepository;

@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final BookRepository bookRepository;

    private final CommentRepository commentRepository;

    public BookRepositoryCustomImpl(@Lazy BookRepository bookRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void deleteByIdWithComments(long id) {
        commentRepository.deleteAllByBookId(id);
        bookRepository.deleteById(id);
    }
}
