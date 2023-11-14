MERGE INTO AUTHORS(FORENAME, SURNAME)
KEY (FORENAME, SURNAME)
VALUES ('AuthorForename1', 'AuthorSurname1'),
       ('AuthorForename2', 'AuthorSurname2'),
       ('AuthorForename3', 'AuthorSurname3');

MERGE INTO GENRES(NAME)
KEY (NAME)
VALUES ('Genre1'),
       ('Genre2'),
       ('Genre3'),
       ('Genre4'),
       ('Genre5');

MERGE INTO BOOKS(TITLE, AUTHORID, GENREID)
KEY (TITLE, AUTHORID, GENREID)
VALUES ('Book1', 1, 1),
       ('Book2', 2, 2),
       ('Book3', 1, 2);

MERGE INTO COMMENTS(TEXT, BOOKID)
KEY (TEXT, BOOKID)
VALUES ('Comment1', 1),
       ('Comment2', 2),
       ('Comment3', 3),
       ('Comment4', 1),
       ('Comment5', 1);