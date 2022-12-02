package ru.yandex.practicum.filmorate.storage.genre;

import ru.yandex.practicum.filmorate.model.Film;

public interface FilmGenresStorage {
    void createGenreByFilm(Film film);

    void updateGenreFilm(Film film);
}
