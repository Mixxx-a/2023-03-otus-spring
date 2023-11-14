package ru.sladkov.otus.spring.hw06.service;

import ru.sladkov.otus.spring.hw06.domain.Comment;
import ru.sladkov.otus.spring.hw06.dto.CommentDto;

import java.util.List;

public interface CommentService {

    Comment create(CommentDto commentDto);

    Comment getById(long id);

    List<Comment> getAllByBookId(long bookId);

    void update(CommentDto newCommentDto);

    void deleteById(long id);
}
