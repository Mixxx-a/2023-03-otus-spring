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

MERGE INTO USERS(USERNAME, PASSWORD)
KEY (USERNAME, PASSWORD)
VALUES ('admin', '$2a$10$ekEH4RlpRixVxIhcTTHKrO4YLAr/QWn/YuBZwMXytowUq5eDect7W'), -- admin
       ('user', '$2a$10$Tq2zga6Ne.53ZcXfl8Jq9.qFQI3QEPY/ov.JpY4yRj8qpm3l.VRVq'); -- password

MERGE INTO ROLES(NAME)
KEY (NAME)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

MERGE INTO USERS_ROLES(USERID, ROLEID)
KEY (USERID, ROLEID)
VALUES (1, 1),
       (1, 2),
       (2, 2);