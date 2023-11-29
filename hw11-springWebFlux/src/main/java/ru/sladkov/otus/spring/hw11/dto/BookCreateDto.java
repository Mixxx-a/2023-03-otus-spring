package ru.sladkov.otus.spring.hw11.dto;

import jakarta.validation.constraints.NotBlank;
import ru.sladkov.otus.spring.hw11.domain.Book;
import ru.sladkov.otus.spring.hw11.repository.AuthorRepository;
import ru.sladkov.otus.spring.hw11.repository.GenreRepository;

public record BookCreateDto(@NotBlank String title, @NotBlank String authorId, @NotBlank String genreId) {

    public Book toDomain(AuthorRepository authorRepository, GenreRepository genreRepository) {
        return new Book(null, title, authorRepository.findById(authorId).block(),
                genreRepository.findById(genreId).block());
    }
}
