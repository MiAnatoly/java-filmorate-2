package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmRepository {
    Film addFilm(Film film);

    Film updateFilm(Film filmToUpdate);

    List<Film> getAllFilms();

    void clear();
}
