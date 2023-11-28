package ru.sladkov.otus.spring.hw14.batch.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import ru.sladkov.otus.spring.hw14.domain.jpa.AuthorJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.BookJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.CommentJpa;
import ru.sladkov.otus.spring.hw14.domain.jpa.GenreJpa;
import ru.sladkov.otus.spring.hw14.domain.mongo.AuthorMongo;
import ru.sladkov.otus.spring.hw14.domain.mongo.BookMongo;
import ru.sladkov.otus.spring.hw14.domain.mongo.CommentMongo;
import ru.sladkov.otus.spring.hw14.domain.mongo.GenreMongo;

@SuppressWarnings("unused")
@Configuration
public class StepConfig {

    private final JobRepository jobRepository;

    private final PlatformTransactionManager platformTransactionManager;

    public StepConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public Step migrateAuthorsStep(ItemReader<AuthorMongo> authorMongoReader,
                                   ItemProcessor<AuthorMongo, AuthorJpa> authorProcessor,
                                   ItemWriter<AuthorJpa> authorJpaWriter) {
        return new StepBuilder("migrateAuthorsStep", jobRepository)
                .<AuthorMongo, AuthorJpa>chunk(5, platformTransactionManager)
                .reader(authorMongoReader)
                .processor(authorProcessor)
                .writer(authorJpaWriter)
                .build();
    }

    @Bean
    public Step migrateGenresStep(ItemReader<GenreMongo> genreMongoReader,
                                  ItemProcessor<GenreMongo, GenreJpa> genreProcessor,
                                  ItemWriter<GenreJpa> genreJpaWriter) {
        return new StepBuilder("migrateGenresStep", jobRepository)
                .<GenreMongo, GenreJpa>chunk(5, platformTransactionManager)
                .reader(genreMongoReader)
                .processor(genreProcessor)
                .writer(genreJpaWriter)
                .build();
    }

    @Bean
    public Step migrateBooksStep(ItemReader<BookMongo> bookMongoReader,
                                 ItemProcessor<BookMongo, BookJpa> bookProcessor,
                                 ItemWriter<BookJpa> bookJpaWriter) {
        return new StepBuilder("migrateBookStep", jobRepository)
                .<BookMongo, BookJpa>chunk(5, platformTransactionManager)
                .reader(bookMongoReader)
                .processor(bookProcessor)
                .writer(bookJpaWriter)
                .build();
    }

    @Bean
    public Step migrateCommentsStep(ItemReader<CommentMongo> commentMongoReader,
                                 ItemProcessor<CommentMongo, CommentJpa> commentProcessor,
                                 ItemWriter<CommentJpa> commentJpaWriter) {
        return new StepBuilder("migrateBookStep", jobRepository)
                .<CommentMongo, CommentJpa>chunk(5, platformTransactionManager)
                .reader(commentMongoReader)
                .processor(commentProcessor)
                .writer(commentJpaWriter)
                .build();
    }
}
