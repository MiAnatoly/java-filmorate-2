package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    List<Film> getFilms();

    List<Film> getFilmsAndGenres();

    Film createFilm(Film film);

    Film updateFilm(Film film);

    void deleteFilm(int id);

    Film findById(int id);

    void deleteFilm(Film film);

    List<Film> popularFilms(int count);
}
