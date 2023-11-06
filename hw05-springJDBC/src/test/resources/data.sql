INSERT INTO AUTHORS(FORENAME, SURNAME)
VALUES ('AuthorForename1', 'AuthorSurname1'),
       ('AuthorForename2', 'AuthorSurname2'),
       ('AuthorForename3', 'AuthorSurname3');

INSERT INTO GENRES(NAME)
VALUES ('Genre1'),
       ('Genre2'),
       ('Genre3'),
       ('Genre4'),
       ('Genre5');

INSERT INTO BOOKS(TITLE, AUTHORID, GENREID)
VALUES ('Book1', 1, 1),
       ('Book2', 2, 2),
       ('Book3', 1, 2);