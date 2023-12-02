package ru.sladkov.otus.spring.hw14.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import ru.sladkov.otus.spring.hw14.domain.jpa.AuthorJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.BookJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.GenreJpa;
import ru.sladkov.otus.spring.hw14.domain.mongo.BookMongo;
import ru.sladkov.otus.spring.hw14.repository.jpa.AuthorJpaRepository;
import ru.sladkov.otus.spring.hw14.repository.jpa.BookJpaRepository;
import ru.sladkov.otus.spring.hw14.repository.jpa.GenreJpaRepository;

import java.util.Map;

public class BookProcessor implements ItemProcessor<BookMongo, BookJpa> {

    private final BookJpaRepository bookJpaRepository;

    private final Map<String, Long> bookIdsTransitionMap;

    private final AuthorJpaRepository authorJpaRepository;

    private final GenreJpaRepository genreJpaRepository;

    private final Map<String, Long> authorsIdsTransitionMap;

    private final Map<String, Long> genresIdsTransitionMap;

    public BookProcessor(BookProcessorBuilder bookProcessorBuilder) {
        this.bookJpaRepository = bookProcessorBuilder.bookJpaRepository;
        this.bookIdsTransitionMap = bookProcessorBuilder.bookIdsTransitionMap;
        this.authorJpaRepository = bookProcessorBuilder.authorJpaRepository;
        this.genreJpaRepository = bookProcessorBuilder.genreJpaRepository;
        this.authorsIdsTransitionMap = bookProcessorBuilder.authorsIdsTransitionMap;
        this.genresIdsTransitionMap = bookProcessorBuilder.genresIdsTransitionMap;
    }



    @Override
    public BookJpa process(BookMongo bookMongo) {
        Long authorJpaId = authorsIdsTransitionMap.get(bookMongo.getAuthor().getId());
        AuthorJpa authorJpa = authorJpaRepository.findById(authorJpaId)
                .orElseThrow();

        Long genreJpaId = genresIdsTransitionMap.get(bookMongo.getGenre().getId());
        GenreJpa genreJpa = genreJpaRepository.findById(genreJpaId)
                .orElseThrow();

        Long nextId = bookJpaRepository.getNextId();
        bookIdsTransitionMap.put(bookMongo.getId(), nextId);
        return new BookJpa(nextId, bookMongo.getTitle(), authorJpa, genreJpa);
    }

    public static class BookProcessorBuilder {
        private BookJpaRepository bookJpaRepository;

        private Map<String, Long> bookIdsTransitionMap;

        private AuthorJpaRepository authorJpaRepository;

        private GenreJpaRepository genreJpaRepository;

        private Map<String, Long> authorsIdsTransitionMap;

        private Map<String, Long> genresIdsTransitionMap;

        public BookProcessorBuilder setBookJpaRepository(BookJpaRepository bookJpaRepository) {
            this.bookJpaRepository = bookJpaRepository;
            return this;
        }

        public BookProcessorBuilder setBookIdsTransitionMap(Map<String, Long> bookIdsTransitionMap) {
            this.bookIdsTransitionMap = bookIdsTransitionMap;
            return this;
        }

        public BookProcessorBuilder setAuthorJpaRepository(AuthorJpaRepository authorJpaRepository) {
            this.authorJpaRepository = authorJpaRepository;
            return this;
        }

        public BookProcessorBuilder setGenreJpaRepository(GenreJpaRepository genreJpaRepository) {
            this.genreJpaRepository = genreJpaRepository;
            return this;
        }

        public BookProcessorBuilder setAuthorsIdsTransitionMap(Map<String, Long> authorsIdsTransitionMap) {
            this.authorsIdsTransitionMap = authorsIdsTransitionMap;
            return this;
        }

        public BookProcessorBuilder setGenresIdsTransitionMap(Map<String, Long> genresIdsTransitionMap) {
            this.genresIdsTransitionMap = genresIdsTransitionMap;
            return this;
        }

        public BookProcessor build() {
            return new BookProcessor(this);
        }
    }
}
