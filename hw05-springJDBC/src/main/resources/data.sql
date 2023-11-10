MERGE INTO AUTHORS(FORENAME, SURNAME)
KEY (FORENAME, SURNAME)
VALUES ('Andrzej', 'Sapkowski'),
       ('Alexander', 'Pushkin');

MERGE INTO GENRES(NAME)
KEY (NAME)
VALUES ('Fantasy'),
       ('Novel');

MERGE INTO BOOKS(TITLE, AUTHORID, GENREID)
KEY (TITLE, AUTHORID, GENREID)
VALUES ('The Witcher', 1, 1),
       ('Eugene Onegin', 2, 2),
       ('Book3', 1, 2);