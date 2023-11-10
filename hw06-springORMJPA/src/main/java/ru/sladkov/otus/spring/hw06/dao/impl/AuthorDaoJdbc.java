package ru.sladkov.otus.spring.hw06.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.sladkov.otus.spring.hw06.dao.AuthorDao;
import ru.sladkov.otus.spring.hw06.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations;
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new AuthorMapper());
    }

    @Override
    public Optional<Author> getById(long id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("select * from authors where id = :id",
                    Map.of("id", id), new AuthorMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String forename = rs.getString("forename");
            String surname = rs.getString("surname");
            return new Author(id, forename, surname);
        }
    }
}
