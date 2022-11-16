package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.SearchedObjectNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Integer, Film> films;
    private Integer id;

    public InMemoryFilmStorage() {
        this.films = new HashMap<>();
        this.id = 0;
    }

    @Override
    public Film addFilm(Film film) {
        if (film.getId() == null) {
            film.setId(++id);
        }

        if (!films.containsKey(film.getId())) {
            films.put(film.getId(), film);
            return film;
        } else {
            throw new ValidationException("Фильм "
                    + film.getName()
                    + " с id="
                    + film.getId()
                    + " уже существует.");
        }
    }

    @Override
    public Film updateFilm(Film filmToUpdate) {
        if (films.containsKey(filmToUpdate.getId())) {
            films.put(filmToUpdate.getId(), filmToUpdate);
            return filmToUpdate;
        } else {
            throw new SearchedObjectNotFoundException("Фильм "
                    + filmToUpdate.getName()
                    + " с id="
                    + filmToUpdate.getId()
                    + " не найден");
        }
    }

    @Override
    public List<Film> getAllFilms() {
        return new ArrayList<>(films.values());
    }

    @Override
    public Film getById(int filmId) {
        if (films.containsKey(filmId)) {
            return films.get(filmId);
        } else {
            throw new SearchedObjectNotFoundException("Фильм "
                    + " с filmId="
                    + filmId
                    + " не найден");
        }
    }

    @Override
    public void addLike(int filmId, int userId) {
        if (films.containsKey(filmId) && userId > 0) {
            films.get(filmId).getLike().add(userId);
        } else {
            throw new SearchedObjectNotFoundException("Фильм "
                    + " с filmId="
                    + filmId
                    + " не найден"
                    + " или пользователь с userId"
                    + userId);
        }
    }

    @Override
    public void removeLike(int filmId, int userId) {
        if (films.containsKey(filmId) && userId > 0) {
            films.get(filmId).getLike().remove(userId);
        } else {
            throw new SearchedObjectNotFoundException("Фильм "
                    + " с filmId="
                    + filmId
                    + " не найден"
                    + " или пользователь с userId"
                    + userId);
        }
    }

    @Override
    public List<Film> getTop(int count) {
        return films.values().stream()
                .sorted(Comparator.comparingInt(film -> -film.getLike().size()))
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        films.clear();
    }
}
