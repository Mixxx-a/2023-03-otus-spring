package ru.sladkov.otus.spring.hw04.configs.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.sladkov.otus.spring.hw04.configs.LocaleConfig;
import ru.sladkov.otus.spring.hw04.configs.RepositoryConfig;
import ru.sladkov.otus.spring.hw04.configs.TestingServiceConfig;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public record AppProperties(Locale locale, String basePathToCsv, int pointsToPass)
        implements RepositoryConfig, LocaleConfig, TestingServiceConfig {

    @Override
    public String getPathToCsv() {
        return basePathToCsv + "_" + locale + ".csv";
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public int getPointsToPass() {
        return pointsToPass;
    }
}
