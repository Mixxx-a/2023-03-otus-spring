package ru.sladkov.otus.spring.hw04.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw04.configs.LocaleConfig;
import ru.sladkov.otus.spring.hw04.service.LocalizationService;

import java.util.Arrays;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final LocaleConfig localeConfig;

    private final MessageSource messageSource;

    public LocalizationServiceImpl(LocaleConfig localeConfig, MessageSource messageSource) {
        this.localeConfig = localeConfig;
        this.messageSource = messageSource;
    }

    @Override
    public String getLocalizedMessage(String code, Object... args) {
        String[] arguments = Arrays.stream(args)
                .map(String::valueOf)
                .toArray(String[]::new);
        return messageSource.getMessage(code, arguments, localeConfig.getLocale());
    }
}
