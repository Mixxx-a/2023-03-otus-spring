package ru.sladkov.otus.spring.hw16.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookCreateDto(@NotBlank String title, @NotNull Long authorId, @NotNull Long genreId) {
}
