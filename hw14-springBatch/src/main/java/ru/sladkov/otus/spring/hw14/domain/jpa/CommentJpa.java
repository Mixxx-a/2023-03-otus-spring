package ru.sladkov.otus.spring.hw14.domain.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class CommentJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid")
    private BookJpa book;

    public CommentJpa() {
    }

    public CommentJpa(Long id, String text, BookJpa book) {
        this.id = id;
        this.text = text;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public BookJpa getBook() {
        return book;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setBook(BookJpa book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "CommentJpa[id=" + this.id + ", text=" + this.text + ", bookId=" + this.book.getId() + "]";
    }
}
