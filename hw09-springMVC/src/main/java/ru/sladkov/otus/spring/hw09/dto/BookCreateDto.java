package ru.sladkov.otus.spring.hw09.dto;

import jakarta.validation.constraints.NotBlank;

public record BookCreateDto(@NotBlank String title, long authorId, long genreId) {
}
