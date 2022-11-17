package ru.yandex.practicum.filmorate.service.film;

import ru.yandex.practicum.filmorate.dto.FilmDTO;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmService {
    FilmDTO addFilm(Film film);

    FilmDTO updateFilm(Film filmToUpdate);

    List<FilmDTO> getAllFilms();

    FilmDTO getById(int filmId);

    void addLike(int filmId, int userId);

    void removeLike(int filmId, int userId);

    List<FilmDTO> getTop(int count);

    void clear();
}
