package ru.sladkov.otus.spring.hw04.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sladkov.otus.spring.hw04.configs.impl.AppProperties;
import ru.sladkov.otus.spring.hw04.configs.impl.IOServiceStreamsConfig;

import java.io.InputStream;
import java.io.OutputStream;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfig {
    @Bean
    public IOServiceConfig ioServiceConfig(@Value("#{T(System).in}") InputStream input,
                                           @Value("#{T(System).out}") OutputStream output) {
        return new IOServiceStreamsConfig(input, output);
    }
}
