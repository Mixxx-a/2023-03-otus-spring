package ru.sladkov.otus.spring.hw13.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw13.repository.GenreRepository;
import ru.sladkov.otus.spring.hw13.domain.Genre;
import ru.sladkov.otus.spring.hw13.exception.NotFoundException;
import ru.sladkov.otus.spring.hw13.service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getById(long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre with id = " + id + " is not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }
}
