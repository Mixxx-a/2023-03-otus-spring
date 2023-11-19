package ru.sladkov.otus.spring.hw08.service;

import ru.sladkov.otus.spring.hw08.domain.Comment;
import ru.sladkov.otus.spring.hw08.dto.CommentDto;

import java.util.List;

public interface CommentService {

    Comment create(CommentDto commentDto);

    Comment getById(String id);

    List<Comment> getAllByBookId(String bookId);

    void update(CommentDto newCommentDto);

    void deleteById(String id);
}
