package ru.sladkov.otus.spring.hw14.batch.config;

import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.sladkov.otus.spring.hw14.domain.mongo.AuthorMongo;
import ru.sladkov.otus.spring.hw14.domain.mongo.BookMongo;
import ru.sladkov.otus.spring.hw14.domain.mongo.CommentMongo;
import ru.sladkov.otus.spring.hw14.domain.mongo.GenreMongo;

import java.util.Map;

@SuppressWarnings("unused")
@Configuration
public class ItemReaderConfig {

    @Bean
    public MongoItemReader<AuthorMongo> authorMongoReader(MongoTemplate template) {
        return new MongoItemReaderBuilder<AuthorMongo>()
                .name("authorMongoReader")
                .targetType(AuthorMongo.class)
                .template(template)
                .jsonQuery("{}")
                .sorts(Map.of())
                .build();
    }

    @Bean
    public MongoItemReader<GenreMongo> genreMongoReader(MongoTemplate template) {
        return new MongoItemReaderBuilder<GenreMongo>()
                .name("genreMongoReader")
                .targetType(GenreMongo.class)
                .template(template)
                .jsonQuery("{}")
                .sorts(Map.of())
                .build();
    }

    @Bean
    public MongoItemReader<BookMongo> bookMongoReader(MongoTemplate template) {
        return new MongoItemReaderBuilder<BookMongo>()
                .name("bookMongoReader")
                .targetType(BookMongo.class)
                .template(template)
                .jsonQuery("{}")
                .sorts(Map.of())
                .build();
    }

    @Bean
    public MongoItemReader<CommentMongo> commentMongoReader(MongoTemplate template) {
        return new MongoItemReaderBuilder<CommentMongo>()
                .name("commentMongoReader")
                .targetType(CommentMongo.class)
                .template(template)
                .jsonQuery("{}")
                .sorts(Map.of())
                .build();
    }
}
