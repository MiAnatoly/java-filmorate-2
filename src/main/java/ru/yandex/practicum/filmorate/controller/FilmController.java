package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.inMemoryStorage.ManagerProvider;
import ru.yandex.practicum.filmorate.inMemoryStorage.InMemoryFilmRepository;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private final InMemoryFilmRepository filmsManager = ManagerProvider.getDefaultFilmsManager();

    // добавление фильма
    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        log.info("Добавление фильма " + film.getName());
        return filmsManager.addFilm(film);
    }

    // обновление фильма
    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film updateFilm) {
        log.info("Обновление фильма " + updateFilm.getName());
        return filmsManager.updateFilm(updateFilm);
    }

    // получение списка фильмов
    @GetMapping
    public List<Film> getFilms() {
        log.info("Получение списка всех фильмов " + filmsManager.getAllFilms());
        return filmsManager.getAllFilms();
    }
}
