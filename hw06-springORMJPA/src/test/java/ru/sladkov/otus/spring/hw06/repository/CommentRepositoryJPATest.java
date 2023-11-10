package ru.sladkov.otus.spring.hw06.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.sladkov.otus.spring.hw06.domain.Book;
import ru.sladkov.otus.spring.hw06.domain.Comment;
import ru.sladkov.otus.spring.hw06.repository.impl.CommentRepositoryJPA;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CommentRepositoryJPA should")
@DataJpaTest
@Import(CommentRepositoryJPA.class)
public class CommentRepositoryJPATest {

    @Autowired
    private CommentRepositoryJPA commentRepositoryJPA;

    @Autowired
    private TestEntityManager em;

    @DisplayName("return existing comment by id")
    @Test
    void shouldGetCommentByIdExisting() {
        Optional<Comment> optComment = commentRepositoryJPA.findById(4);
        assertThat(optComment.isPresent()).isTrue();
        Comment comment = optComment.get();
        assertThat(comment.getId()).isEqualTo(4);
        assertThat(comment.getText()).isEqualTo("Comment4");
        assertThat(comment.getBook().getId()).isEqualTo(1);
    }

    @DisplayName("return empty optional of non-existing comment by id")
    @Test
    void shouldGetCommentByIdNonExisting() {
        Optional<Comment> optComment = commentRepositoryJPA.findById(10);
        assertThat(optComment.isPresent()).isFalse();
    }

    @DisplayName("return all comments for book by bookId")
    @Test
    void shouldGetCommentsForBookByBookId() {
        List<Comment> comments = commentRepositoryJPA.findAllByBookId(1L);
        assertThat(comments).hasSize(3);
    }

    @DisplayName("save new comment")
    @Test
    void shouldSaveComment() {
        Book book = em.find(Book.class, 2);
        Comment newComment = new Comment(null, "Comment6", book);
        commentRepositoryJPA.save(newComment);

        em.flush();

        Comment comment = em.find(Comment.class, 6);
        assertThat(comment.getId()).isEqualTo(6);
        assertThat(comment.getText()).isEqualTo("Comment6");
        assertThat(comment.getBook().getId()).isEqualTo(book.getId());
    }

    @DisplayName("update existing comment")
    @Test
    void shouldUpdateComment() {
        Book newBook = em.find(Book.class, 3);
        Comment newComment = new Comment(5L, "Comment5Updated", newBook);
        commentRepositoryJPA.save(newComment);

        em.flush();

        Comment comment = em.find(Comment.class, 5);
        assertThat(comment.getId()).isEqualTo(5);
        assertThat(comment.getText()).isEqualTo("Comment5Updated");
        assertThat(comment.getBook().getId()).isEqualTo(newBook.getId());
    }

    @DisplayName("delete comment by id")
    @Test
    void shouldDeleteCommentById() {
        commentRepositoryJPA.deleteById(4);

        em.flush();

        Comment comment = em.find(Comment.class, 4);
        assertThat(comment).isNull();
    }
}
