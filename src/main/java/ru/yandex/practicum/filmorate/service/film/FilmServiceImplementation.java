package ru.yandex.practicum.filmorate.service.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.List;

@Service
public class FilmServiceImplementation implements FilmService {
    private final FilmStorage filmStorage;

    @Autowired
    public FilmServiceImplementation(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    @Override
    public Film addFilm(Film film) {
        return filmStorage.addFilm(film);
    }

    @Override
    public Film updateFilm(Film filmToUpdate) {
        return filmStorage.updateFilm(filmToUpdate);
    }

    @Override
    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    @Override
    public Film getById(int filmId) {
        return filmStorage.getById(filmId);
    }

    @Override
    public void addLike(int filmId, int userId) {
        filmStorage.addLike(filmId, userId);
    }

    @Override
    public void removeLike(int filmId, int userId) {
        filmStorage.removeLike(filmId, userId);
    }

    @Override
    public List<Film> getTop(int count) {
        return filmStorage.getTop(count);
    }

    @Override
    public void clear() {
        filmStorage.clear();
    }
}
