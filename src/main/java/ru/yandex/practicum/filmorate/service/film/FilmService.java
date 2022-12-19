package ru.yandex.practicum.filmorate.service.film;

import ru.yandex.practicum.filmorate.dto.FilmDto;

import java.util.List;

public interface FilmService {
    List<FilmDto> findAll();

    FilmDto createFilm(FilmDto filmDto);

    FilmDto updateFilm(FilmDto filmDto);

    void deleteFilm(int id);

    FilmDto findById(int id);

    void removeFilm(FilmDto filmDto);

    void putLike(int id, int userId);

    void removeLike(int id, int userId);

    List<FilmDto> popularFilms(int count);
}
