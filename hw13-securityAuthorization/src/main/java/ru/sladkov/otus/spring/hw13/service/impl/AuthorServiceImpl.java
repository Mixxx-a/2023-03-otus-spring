package ru.sladkov.otus.spring.hw13.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw13.repository.AuthorRepository;
import ru.sladkov.otus.spring.hw13.domain.Author;
import ru.sladkov.otus.spring.hw13.exception.NotFoundException;
import ru.sladkov.otus.spring.hw13.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author with id = " + id + " is not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorRepository.findAll();
    }
}
