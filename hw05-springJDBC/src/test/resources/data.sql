INSERT INTO AUTHORS(ID, FORENAME, SURNAME)
VALUES (1, 'AuthorForename1', 'AuthorSurname1'),
       (2, 'AuthorForename2', 'AuthorSurname2'),
       (3, 'AuthorForename3', 'AuthorSurname3');

INSERT INTO GENRES(ID, NAME)
VALUES (1, 'Genre1'),
       (2, 'Genre2'),
       (3, 'Genre3'),
       (4, 'Genre4'),
       (5, 'Genre5');

INSERT INTO BOOKS(ID, TITLE, AUTHORID, GENREID)
VALUES (1, 'Book1', 1, 1),
       (2, 'Book2', 2, 2),
       (3, 'Book3', 1, 2);