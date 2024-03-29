package ru.sladkov.otus.spring.hw05.service.impl;

import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw05.dao.GenreDao;
import ru.sladkov.otus.spring.hw05.domain.Genre;
import ru.sladkov.otus.spring.hw05.exception.NotFoundException;
import ru.sladkov.otus.spring.hw05.service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre getGenreById(long id) {
        return genreDao.getById(id)
                .orElseThrow(() -> new NotFoundException("Genre with id = " + id + " is not found"));
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.getAll();
    }
}
