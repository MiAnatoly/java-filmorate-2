package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.FilmDto;
import ru.yandex.practicum.filmorate.service.film.FilmServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {

    private final FilmServiceImpl filmService;

    @GetMapping
    public List<FilmDto> getFilms() {
        return filmService.findAll();
    }

    @PostMapping
    public FilmDto createFilm(@Valid @RequestBody FilmDto filmDto) {
        return filmService.createFilm(filmDto);
    }

    @PutMapping
    public FilmDto updateFilm(@Valid @RequestBody FilmDto filmDto) {
        return filmService.updateFilm(filmDto);
    }

    @GetMapping("/{id}")
    public FilmDto findById(@PathVariable int id) {
        return filmService.findById(id);
    }

    @DeleteMapping
    public void removeFilm(@Valid @RequestBody FilmDto filmDto) {
        filmService.removeFilm(filmDto);
    }

    @PutMapping("/{id}/like/{userId}")
    public void putLike(@PathVariable int id, @PathVariable int userId) {
        filmService.putLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void removeLike(@PathVariable int id, @PathVariable int userId) {
        filmService.removeLike(id, userId);
    }

    @GetMapping("/popular")
    public List<FilmDto> popularFilms(@RequestParam(value = "count", defaultValue = "10", required = false) int count) {
        return filmService.popularFilms(count);
    }
}
