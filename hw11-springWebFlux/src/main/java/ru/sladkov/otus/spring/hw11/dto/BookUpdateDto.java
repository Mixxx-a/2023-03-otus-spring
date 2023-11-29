package ru.sladkov.otus.spring.hw11.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookUpdateDto(@NotNull String id, @NotBlank String title, @NotNull String authorId,
                            @NotNull String genreId) {
}
