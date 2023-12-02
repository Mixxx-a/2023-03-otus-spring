package ru.sladkov.otus.spring.hw16.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.sladkov.otus.spring.hw16.service.BookService;

@SuppressWarnings("unused")
@Component
public class BooksCountIndicator implements HealthIndicator {

    private final BookService bookService;

    public BooksCountIndicator(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public Health health() {
        Long booksCount = bookService.getCount();
        if (booksCount > 0) {
            return Health.up()
                    .withDetail("message", "Book count: " + booksCount)
                    .build();
        } else {
            return Health.down()
                    .withDetail("message", "Something wrong: there are no books in library!")
                    .build();
        }
    }
}
