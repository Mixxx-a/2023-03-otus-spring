package ru.sladkov.otus.spring.hw08.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.sladkov.otus.spring.hw08.domain.Book;
import ru.sladkov.otus.spring.hw08.domain.Comment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CommentRepository should")
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("correctly create and read comment")
    @Test
    void shouldCreateReadComment() {
        Book book = em.find(Book.class, 2);
        Comment newComment = new Comment(null, "Comment6", book);
        commentRepository.save(newComment);

        em.detach(book);
        em.detach(newComment);

        Optional<Comment> optComment = commentRepository.findById(6L);
        assertThat(optComment.isPresent()).isTrue();
        Comment comment = optComment.get();
        assertThat(comment.getId()).isEqualTo(6);
        assertThat(comment.getText()).isEqualTo("Comment6");
        assertThat(comment.getBook().getId()).isEqualTo(book.getId());
    }

    @DisplayName("return all comments for book by bookId")
    @Test
    void shouldGetCommentsForBookByBookId() {
        List<Comment> comments = commentRepository.findByBookId(1L);
        assertThat(comments).hasSize(3);
    }
}
