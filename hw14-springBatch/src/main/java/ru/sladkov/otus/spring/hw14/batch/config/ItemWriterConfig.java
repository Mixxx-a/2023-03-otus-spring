package ru.sladkov.otus.spring.hw14.batch.config;

import jakarta.persistence.EntityManager;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sladkov.otus.spring.hw14.domain.jpa.AuthorJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.BookJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.CommentJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.GenreJpa;

@SuppressWarnings("unused")
@Configuration
public class ItemWriterConfig {

    @Bean
    public JpaItemWriter<AuthorJpa> authorJpaWriter(EntityManager entityManager) {
        return new JpaItemWriterBuilder<AuthorJpa>()
                .entityManagerFactory(entityManager.getEntityManagerFactory())
                .build();
    }

    @Bean
    public JpaItemWriter<GenreJpa> genreJpaWriter(EntityManager entityManager) {
        return new JpaItemWriterBuilder<GenreJpa>()
                .entityManagerFactory(entityManager.getEntityManagerFactory())
                .build();
    }

    @Bean
    public JpaItemWriter<BookJpa> bookJpaWriter(EntityManager entityManager) {
        return new JpaItemWriterBuilder<BookJpa>()
                .entityManagerFactory(entityManager.getEntityManagerFactory())
                .build();
    }

    @Bean
    public JpaItemWriter<CommentJpa> commentJpaWriter(EntityManager entityManager) {
        return new JpaItemWriterBuilder<CommentJpa>()
                .entityManagerFactory(entityManager.getEntityManagerFactory())
                .build();
    }
}
