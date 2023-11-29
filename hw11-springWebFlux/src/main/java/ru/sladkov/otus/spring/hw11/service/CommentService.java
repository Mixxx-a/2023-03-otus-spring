package ru.sladkov.otus.spring.hw11.service;

import ru.sladkov.otus.spring.hw11.domain.Comment;
import ru.sladkov.otus.spring.hw11.dto.CommentDto;

import java.util.List;

public interface CommentService {

    Comment create(CommentDto commentDto);

    Comment getById(String id);

    List<Comment> getAllByBookId(String bookId);

    void update(CommentDto newCommentDto);

    void deleteById(String id);
}
