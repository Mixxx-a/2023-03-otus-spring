package ru.sladkov.otus.spring.hw06.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.sladkov.otus.spring.hw06.dao.BookDao;
import ru.sladkov.otus.spring.hw06.domain.Author;
import ru.sladkov.otus.spring.hw06.domain.Book;
import ru.sladkov.otus.spring.hw06.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public Book create(Book book) {
        MapSqlParameterSource params = fillBookParameters(book);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into books (title, authorid, genreid) values (:title, :authorid, :genreid)",
                params, keyHolder);
        Long generatedKey = keyHolder.getKey()
                .longValue();
        return new Book(generatedKey, book.title(), book.author(), book.genre());
    }

    @Override
    public void update(Book book) {
        MapSqlParameterSource params = fillBookParameters(book);
        jdbc.update("update books set title = :title, authorid = :authorid, genreid = :genreid where id = :id",
                params);
    }

    @Override
    public Optional<Book> getById(long id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("select * from books" +
                            " join authors on books.authorid = authors.id" +
                            " join genres on books.genreid = genres.id" +
                            " where books.id = :id",
                    Map.of("id", id), new BookMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select * from books" +
                        " join authors on books.authorid = authors.id" +
                        " join genres on books.genreid = genres.id",
                new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from books where id = :id", Map.of("id", id));
    }

    private MapSqlParameterSource fillBookParameters(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", book.id());
        params.addValue("title", book.title());
        params.addValue("authorid", book.author().id());
        params.addValue("genreid", book.genre().id());
        return params;
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author(rs.getLong("authorid"), rs.getString("forename"),
                    rs.getString("surname"));
            Genre genre = new Genre(rs.getLong("genreid"), rs.getString("name"));
            return new Book(rs.getLong("id"), rs.getString("title"),
                    author, genre);
        }
    }

}
