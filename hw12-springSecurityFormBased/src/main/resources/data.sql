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

MERGE INTO COMMENTS(TEXT, BOOKID)
KEY (TEXT, BOOKID)
VALUES ('Very Nice Fantasy called The Witcher!', 1),
       ('Cool Novel called Eugene Onegin!', 2),
       ('Did not really like it', 1),
       ('Comment for Book1 1', 1),
       ('Comment for Book1 2', 1);