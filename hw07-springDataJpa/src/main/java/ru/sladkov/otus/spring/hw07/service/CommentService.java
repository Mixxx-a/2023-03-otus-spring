package ru.sladkov.otus.spring.hw07.service;

import ru.sladkov.otus.spring.hw07.domain.Comment;
import ru.sladkov.otus.spring.hw07.dto.CommentDto;

import java.util.List;

public interface CommentService {

    Comment create(CommentDto commentDto);

    Comment getById(long id);

    List<Comment> getAllByBookId(long bookId);

    void update(CommentDto newCommentDto);

    void deleteById(long id);
}
