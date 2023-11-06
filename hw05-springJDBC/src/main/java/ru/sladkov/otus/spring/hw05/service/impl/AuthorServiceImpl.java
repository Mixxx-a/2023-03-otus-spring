package ru.sladkov.otus.spring.hw05.service.impl;

import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw05.dao.AuthorDao;
import ru.sladkov.otus.spring.hw05.domain.Author;
import ru.sladkov.otus.spring.hw05.exception.NotFoundException;
import ru.sladkov.otus.spring.hw05.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author getAuthorById(long id) throws NotFoundException {
        return authorDao.getById(id).orElseThrow(() ->
                new NotFoundException("Author with id = " + id + " is not found"));
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorDao.getAll();
    }
}
