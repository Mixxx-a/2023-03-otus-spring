package ru.sladkov.otus.spring.hw14.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("unused")
@Configuration
public class JobConfig {

    private final JobRepository jobRepository;

    public JobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job migrateLibraryJob(Step migrateAuthorsStep, Step migrateGenresStep, Step migrateBooksStep,
                                 Step migrateCommentsStep) {
        return new JobBuilder("MigrateLibraryJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(migrateAuthorsStep)
                .next(migrateGenresStep)
                .next(migrateBooksStep)
                .next(migrateCommentsStep)
                .end()
                .build();
    }
}
