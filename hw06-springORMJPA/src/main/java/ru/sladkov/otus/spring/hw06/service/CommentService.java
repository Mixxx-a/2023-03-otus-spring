package ru.sladkov.otus.spring.hw06.service;

import ru.sladkov.otus.spring.hw06.domain.Comment;
import ru.sladkov.otus.spring.hw06.dto.CommentDto;

import java.util.List;

public interface CommentService {

    Comment createComment(CommentDto commentDTO);

    Comment getComment(long id);

    List<Comment> getAllCommentsByBookId(long bookId);

    void updateComment(CommentDto newCommentDto);

    void deleteComment(long id);
}
