package ru.sladkov.otus.spring.hw14.domain.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
@NamedEntityGraph(name = "book-entity-graph",
        attributeNodes = {@NamedAttributeNode("author"), @NamedAttributeNode("genre")})
public class BookJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorid")
    private AuthorJpa author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genreid")
    private GenreJpa genre;

    public BookJpa() {
    }

    public BookJpa(Long id, String title, AuthorJpa author, GenreJpa genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public AuthorJpa getAuthor() {
        return author;
    }

    public GenreJpa getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(AuthorJpa authorJpa) {
        this.author = authorJpa;
    }

    public void setGenre(GenreJpa genreJpa) {
        this.genre = genreJpa;
    }

    @Override
    public String toString() {
        return "BookJpa[id=" + this.id
                + ", title=" + this.title
                + ", authorJpa=" + this.author
                + ", genreJpa=" + this.genre + "]";
    }
}
