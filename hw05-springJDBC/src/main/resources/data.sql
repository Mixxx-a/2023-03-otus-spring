INSERT INTO AUTHORS(ID, FORENAME, SURNAME)
VALUES (1, 'Andrzej', 'Sapkowski'),
       (2, 'Alexander', 'Pushkin');

INSERT INTO GENRES(ID, NAME)
VALUES (1, 'Fantasy'),
       (2, 'Detective');

INSERT INTO BOOKS(ID, TITLE, AUTHORID, GENREID)
VALUES (1, 'book1', 1, 1),
       (2, 'book2', 2, 2),
       (3, 'book3', 1, 2);