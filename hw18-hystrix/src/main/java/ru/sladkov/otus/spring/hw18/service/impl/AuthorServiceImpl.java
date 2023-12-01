package ru.sladkov.otus.spring.hw18.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw18.repository.AuthorRepository;
import ru.sladkov.otus.spring.hw18.domain.Author;
import ru.sladkov.otus.spring.hw18.exception.NotFoundException;
import ru.sladkov.otus.spring.hw18.service.AuthorService;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final Author dummyAuthor = new Author(1L, "Forename 1", "Surname 1");

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand(commandKey = "getAuthor", fallbackMethod = "getDummyAuthor", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public Author getById(long id) {
        Util.sleepRandomly();
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Author with id = " + id + " is not found"));
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand(commandKey = "getAuthors", fallbackMethod = "getDummyAuthors", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public List<Author> getAll() {
        Util.sleepRandomly();
        return authorRepository.findAll();
    }

    private Author getDummyAuthor(long id) {
        return dummyAuthor;
    }

    private List<Author> getDummyAuthors() {
        return List.of(
                dummyAuthor,
                new Author(2L, "Forename 2", "Surname 2"),
                new Author(3L, "Forename 3", "Surname 3")
        );
    }
}
