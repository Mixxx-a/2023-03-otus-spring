package ru.sladkov.otus.spring.hw18.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw18.domain.Genre;
import ru.sladkov.otus.spring.hw18.exception.NotFoundException;
import ru.sladkov.otus.spring.hw18.repository.GenreRepository;
import ru.sladkov.otus.spring.hw18.service.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final Genre dummyGenre = new Genre(1L, "Genre 1");

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand(commandKey = "getGenre", fallbackMethod = "getDummyGenre")
    public Genre getById(long id) {
        Util.sleepRandomly();
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre with id = " + id + " is not found"));
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand(commandKey = "getGenres", fallbackMethod = "getDummyGenres")
    public List<Genre> getAll() {
        Util.sleepRandomly();
        return genreRepository.findAll();
    }

    @SuppressWarnings("unused")
    private Genre getDummyGenre(long id) {
        return dummyGenre;
    }

    @SuppressWarnings("unused")
    private List<Genre> getDummyGenres() {
        return List.of(
                dummyGenre,
                new Genre(2L, "Genre 2"),
                new Genre(3L, "Genre 3")
        );
    }
}
