package ru.sladkov.otus.spring.hw10.domain;

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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookid")
    private Book book;

    public Comment() {
    }

    public Comment(Long id, String text, Book book) {
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

    public Book getBook() {
        return book;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Comment[id=" + this.id + ", text=" + this.text + ", bookId=" + this.book.getId() + "]";
    }
}
