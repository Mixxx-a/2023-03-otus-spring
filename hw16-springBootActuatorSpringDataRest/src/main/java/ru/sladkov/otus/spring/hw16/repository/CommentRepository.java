package ru.sladkov.otus.spring.hw16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sladkov.otus.spring.hw16.domain.Comment;

import java.util.List;

@RepositoryRestResource(path = "comment")
public interface CommentRepository extends JpaRepository<Comment, Long> {

//    http://localhost:8080/datarest/comment/search/bookid?bookid=1
    @RestResource(path = "bookid", rel = "bookid")
    List<Comment> findByBookId(Long bookid);

    @RestResource(exported = false)
    void deleteAllByBookId(Long bookId);

}
