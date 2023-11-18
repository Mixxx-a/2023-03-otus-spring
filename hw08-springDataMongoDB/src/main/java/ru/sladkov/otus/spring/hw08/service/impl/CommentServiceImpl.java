package ru.sladkov.otus.spring.hw08.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw08.domain.Book;
import ru.sladkov.otus.spring.hw08.domain.Comment;
import ru.sladkov.otus.spring.hw08.dto.CommentDto;
import ru.sladkov.otus.spring.hw08.exception.CommentModificationException;
import ru.sladkov.otus.spring.hw08.exception.NotFoundException;
import ru.sladkov.otus.spring.hw08.repository.BookRepository;
import ru.sladkov.otus.spring.hw08.repository.CommentRepository;
import ru.sladkov.otus.spring.hw08.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public Comment create(CommentDto commentDto) {
        Long bookId = commentDto.bookId();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Create comment: Book with id = " + bookId + " is not found"));

        Comment comment = new Comment(null, commentDto.text(), book);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getById(long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Comment with id = " + id + " is not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllByBookId(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new NotFoundException("Get comments for book: book with id = " + bookId + " is not found"));
        return commentRepository.findByBookId(book.getId());
    }

    @Override
    @Transactional
    public void update(CommentDto newCommentDto) {
        Long commentId = newCommentDto.id();
        Comment existingComment = commentRepository.findById(commentId).orElseThrow(() ->
                new NotFoundException("Update comment: Comment with id = " + commentId + " is not found"));

        Long newBookId = newCommentDto.bookId();
        if (!newBookId.equals(existingComment.getBook().getId())) {
            throw new CommentModificationException("Update comment: can not assign this comment to different book");
        }

        existingComment.setText(newCommentDto.text());
        commentRepository.save(existingComment);

    }

    @Override
    @Transactional
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}
