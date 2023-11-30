package ru.sladkov.otus.spring.hw16.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.sladkov.otus.spring.hw16.dto.BookCreateDto;
import ru.sladkov.otus.spring.hw16.dto.BookDto;
import ru.sladkov.otus.spring.hw16.dto.BookUpdateDto;
import ru.sladkov.otus.spring.hw16.exception.NotFoundException;
import ru.sladkov.otus.spring.hw16.service.impl.BookServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@DisplayName("BookServiceImpl should")
public class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookService;

    @DisplayName("return existing book by id")
    @Test
    void shouldGetBookByIdExisting() {
        BookDto bookDto = bookService.getById(2);
        assertThat(bookDto.getId()).isEqualTo(2);
        assertThat(bookDto.getTitle()).isEqualTo("Book2");
    }

    @DisplayName("throw NotFoundException of non-existing book by id")
    @Test
    void shouldGetBookByIdNonExisting() {
        assertThatThrownBy(() -> bookService.getById(4))
                .isInstanceOf(NotFoundException.class);
    }

    @DisplayName("create new book")
    @Test
    @Transactional
    void shouldCreateBook() {
        bookService.create(new BookCreateDto("Book4", 1L, 1L));

        BookDto createdBookDto = bookService.getById(4);
        assertThat(createdBookDto.getId()).isEqualTo(4);
        assertThat(createdBookDto.getTitle()).isEqualTo("Book4");
    }

    @DisplayName("not create book with non-existing author and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotCreateBookNonExistingAuthor() {
        assertThatThrownBy(() -> bookService.create(new BookCreateDto("Book4", 123L, 1L)))
                .isInstanceOf(NotFoundException.class);

        assertThatThrownBy(() -> bookService.getById(4))
                .isInstanceOf(NotFoundException.class);
    }

    @DisplayName("not create book with non-existing genre and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotCreateBookNonExistingGenre() {
        assertThatThrownBy(() -> bookService.create(new BookCreateDto("Book4", 1L, 123L)))
                .isInstanceOf(NotFoundException.class);

        assertThatThrownBy(() -> bookService.getById(4))
                .isInstanceOf(NotFoundException.class);
    }

    @DisplayName("update book")
    @Test
    @Transactional
    void shouldUpdateBook() {
        bookService.update(new BookUpdateDto(3L, "Book3New", 1L, 1L));

        BookDto updatedBookDto = bookService.getById(3);
        assertThat(updatedBookDto.getId()).isEqualTo(3);
        assertThat(updatedBookDto.getTitle()).isEqualTo("Book3New");
    }

    @DisplayName("not update non-existing book and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotUpdateNonExistingBook() {
        assertThatThrownBy(() -> bookService.update(new BookUpdateDto(4L, "Book4", 1L, 1L)))
                .isInstanceOf(NotFoundException.class);

        assertThatThrownBy(() -> bookService.getById(4))
                .isInstanceOf(NotFoundException.class);
    }

    @DisplayName("not update book with non-existing author and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotUpdateBookNonExistingAuthor() {
        assertThatThrownBy(() -> bookService.update(new BookUpdateDto(3L, "Book3New", 123L, 1L)))
                .isInstanceOf(NotFoundException.class);

        BookDto bookDto = bookService.getById(3);
        assertThat(bookDto.getId()).isEqualTo(3);
        assertThat(bookDto.getTitle()).isEqualTo("Book3");
    }

    @DisplayName("not update book with non-existing genre and throw NotFoundException")
    @Test
    @Transactional
    void shouldNotUpdateBookNonExistingGenre() {
        assertThatThrownBy(() -> bookService.update(new BookUpdateDto(3L, "Book3New", 1L, 123L)))
                .isInstanceOf(NotFoundException.class);

        BookDto bookDto = bookService.getById(3);
        assertThat(bookDto.getId()).isEqualTo(3);
        assertThat(bookDto.getTitle()).isEqualTo("Book3");
    }

    @DisplayName("delete book")
    @Test
    @Transactional
    void shouldDeleteBook() {
        bookService.deleteById(1);

        assertThatThrownBy(() -> bookService.getById(1))
                .isInstanceOf(NotFoundException.class);
    }
}
