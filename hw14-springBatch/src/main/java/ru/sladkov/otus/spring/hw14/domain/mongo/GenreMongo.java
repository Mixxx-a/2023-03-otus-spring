package ru.sladkov.otus.spring.hw14.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genres")
public class GenreMongo {
    @Id
    private String id;

    private String name;

    public GenreMongo() {
    }

    public GenreMongo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenreMongo(String name) {
        this(null, name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "GenreMongo[id=" + this.id + ", name=" + this.name + "]";
    }
}
