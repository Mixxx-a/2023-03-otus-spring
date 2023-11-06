INSERT INTO AUTHORS(FORENAME, SURNAME)
VALUES ('Andrzej', 'Sapkowski'),
       ('Alexander', 'Pushkin');

INSERT INTO GENRES(NAME)
VALUES ('Fantasy'),
       ('Novel');

INSERT INTO BOOKS(TITLE, AUTHORID, GENREID)
VALUES ('The Witcher', 1, 1),
       ('Eugene Onegin', 2, 2),
       ('Book3', 1, 2);