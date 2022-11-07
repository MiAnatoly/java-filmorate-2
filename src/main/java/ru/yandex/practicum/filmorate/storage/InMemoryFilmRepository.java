package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryFilmRepository implements FilmRepository {
    private final Map<Integer, Film> films;
    private Integer id;

    public InMemoryFilmRepository() {
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
            throw new ValidationException("Фильм "
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
    public void clear() {
        films.clear();
    }
}
