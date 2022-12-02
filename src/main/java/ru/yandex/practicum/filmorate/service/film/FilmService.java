package ru.yandex.practicum.filmorate.service.film;

import ru.yandex.practicum.filmorate.dto.FilmDto;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmService {
    void loadData(Film film);

    List<FilmDto> findAll();

    FilmDto createFilm(FilmDto filmDto);

    FilmDto updateFilm(FilmDto filmDto);

    FilmDto findById(int id);

    void removeFilm(FilmDto filmDto);

    void putLike(int id, int userId);

    void removeLike(int id, int userId);

    List<FilmDto> popularFilms(int count);
}
