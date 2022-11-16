package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.film.FilmService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    // добавление фильма
    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        log.info("Добавление фильма " + film.getName());
        return filmService.addFilm(film);
    }

    // обновление фильма
    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film updateFilm) {
        log.info("Обновление фильма " + updateFilm.getName());
        return filmService.updateFilm(updateFilm);
    }

    // получение списка фильмов
    @GetMapping
    public List<Film> getFilms() {
        log.info("Получение списка всех фильмов " + filmService.getAllFilms());
        return filmService.getAllFilms();
    }

    @GetMapping("/{filmId}")
    public Film getById(@Valid @PathVariable int filmId) {
        log.info("Запрос фильма по id=" + filmId);
        return filmService.getById(filmId);
    }

    // пользователь ставит лайк фильму
    @PutMapping("/{filmId}/like/{userId}")
    public void addLike(@Valid @PathVariable int filmId, @PathVariable int userId) {
        log.info("Пользователь ставит лайк фильму filmId=" + filmId + " userId=" + userId);
        filmService.addLike(filmId, userId);
    }

    // пользователь удаляет лайк
    @DeleteMapping("/{filmId}/like/{userId}")
    public void removeLike(@Valid @PathVariable int filmId, @PathVariable int userId) {
        log.info("Пользователь удаляет лайк фильму filmId=" + filmId + " userId=" + userId);
        filmService.removeLike(filmId, userId);
    }

    // возвращает список из первых count фильмов по количеству лайков
    @GetMapping("/popular")
    public List<Film> getPopular(@Valid @RequestParam(required = false, defaultValue = "10") int count) {
        return filmService.getTop(count);
    }
}
