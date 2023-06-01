package ru.sladkov.otus.spring.hw04.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

@Configuration
public class AppConfig {

    @Bean
    public RepositoryConfig repositoryConfig(
            @Value("${application.repository.base-path-to-csv}") String pathToCsv) {
        return new RepositoryConfig(pathToCsv);
    }

    @Bean
    public TestingServiceConfig testingServiceConfig(
            @Value("${application.testingservice.points-to-pass}") int pointsToPass) {
        return new TestingServiceConfig(pointsToPass);
    }

    @Bean
    public LocaleConfig localeConfig(@Value("${application.locale}") Locale locale) {
        return new LocaleConfig(locale);
    }

    @Bean
    public IOServiceConfig ioServiceConfig(@Value("#{T(System).in}") InputStream input,
                                           @Value("#{T(System).out}") OutputStream output) {
        return new IOServiceConfig(input, output);
    }
}
