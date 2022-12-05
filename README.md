# java-filmorate
Спринт 11.

## Схема БД.

![alt text](/src/main/resources/FILMS.png)

## Примеры запросов.

Выборка всех фильмов.

`SELECT F.FILM_ID, 
        F.NAME,F.DESCRIPTION, 
        F.RELEASE_DATE,
        F.DURATION,
        F.MPA_ID,
        R.MPA_NAME
FROM FILMS f
JOIN MPA_RATINGS AS R ON f.MPA_ID = R.MPA_ID
ORDER BY F.FILM_ID;`

Добавление фильма.

`INSERT INTO films (NAME, DESCRIPTION, RELEASE_DATE, DURATION, MPA_ID)
VALUES (?, ?, ?, ?, ?);`

Обновление фильма.

`UPDATE FILMS SET NAME = ?, DESCRIPTION = ?, RELEASE_DATE = ?, DURATION = ?, MPA_ID = ?
WHERE FILM_ID = ?;`

Поиск фильма по id.

`SELECT F.FILM_ID, 
        F.NAME, 
        F.DESCRIPTION, 
        F.RELEASE_DATE,
        F.DURATION, 
        F.MPA_ID,
        R.MPA_NAME
FROM FILMS F
JOIN MPA_RATINGS AS R ON F.MPA_ID = R.MPA_ID
WHERE F.FILM_ID = ?;`

Получение списка пользователей.

`SELECT * FROM USERS;`

Добавление пользователя.

`INSERT INTO USERS(EMAIL, LOGIN, USER_NAME, BIRTHDAY) values (?, ?, ?, ?);`

Обновление пользователя.

`UPDATE USERS SET EMAIL = ?,  LOGIN = ?, USER_NAME = ?, BIRTHDAY = ?
WHERE USER_ID = ?;`

Поиск пользователя по id.

`SELECT * FROM USERS WHERE USER_ID = ?;`

Удаление пользователя.

`DELETE FROM USERS WHERE USER_ID = ?;`