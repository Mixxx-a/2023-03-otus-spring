package ru.sladkov.otus.spring.hw15.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.PollerSpec;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.sladkov.otus.spring.hw15.service.EngineService;
import ru.sladkov.otus.spring.hw15.service.BodyService;

@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannelSpec<?, ?> orderChannel() {
        return MessageChannels.queue(10);
    }

    @Bean
    public MessageChannelSpec<?, ?> carChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerSpec poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2);
    }

    @Bean
    public IntegrationFlow carFlow(EngineService engineService, BodyService bodyService) {
        return IntegrationFlow.from(orderChannel())
                .bridge()
                .handle(bodyService, "buildBody")
                .handle(engineService, "assembleEngine")
                .channel(carChannel())
                .get();
    }
}
