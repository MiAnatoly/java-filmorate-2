package ru.yandex.practicum.filmorate.service.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.genre.FilmGenresStorage;
import ru.yandex.practicum.filmorate.storage.genre.GenreStorage;
import ru.yandex.practicum.filmorate.storage.like.LikeStorage;
import ru.yandex.practicum.filmorate.dto.FilmDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FilmServiceImpl implements FilmService {
    private final FilmStorage filmStorage;
    private final LikeStorage likeStorage;
    private final GenreStorage genreStorage;
    private final FilmGenresStorage filmGenresStorage;

    @Autowired
    public FilmServiceImpl(FilmStorage filmStorage,
                           LikeStorage likeStorage,
                           GenreStorage genreStorage,
                           FilmGenresStorage filmGenresStorage) {
        this.filmStorage = filmStorage;
        this.likeStorage = likeStorage;
        this.genreStorage = genreStorage;
        this.filmGenresStorage = filmGenresStorage;
    }

    public void loadData(Film film) {
        film.setGenres(genreStorage.getGenresByFilm(film));
    }

    public List<FilmDto> findAll() {
        List<Film> films = filmStorage.getFilms();
        for (Film film : films) {
            loadData(film);
        }
        return films.stream()
                .map(this::convertFilmToDto)
                .collect(Collectors.toList());
    }

    public FilmDto createFilm(FilmDto filmDto) {
        Film film = convertFilmDtoToFilm(filmDto);
        Film newFilm = filmStorage.createFilm(film);
        filmGenresStorage.createGenreByFilm(newFilm);
        return convertFilmToDto(newFilm);
    }

    public FilmDto updateFilm(FilmDto filmDto) {
        Film film = convertFilmDtoToFilm(filmDto);
        filmGenresStorage.updateGenreFilm(film);
        filmGenresStorage.createGenreByFilm(film);
        return convertFilmToDto(filmStorage.updateFilm(film));
    }

    public FilmDto findById(int id) {
        Film film = filmStorage.findById(id);
        loadData(film);
        log.info("По id {} найден фильм {}", id, film.getName());
        return convertFilmToDto(film);
    }

    public void removeFilm(FilmDto filmDto) {
        Film film = convertFilmDtoToFilm(filmDto);
        filmStorage.deleteFilm(film);
    }

    public void putLike(int id, int userId) {
        if (filmStorage.getFilms().contains(filmStorage.findById(id))) {
            log.info("Пользователь с id {} поставил лайк фильму {}.", userId, filmStorage.findById(id).getName());
            likeStorage.putLike(id, userId);
            filmStorage.findById(id).getLikes().add(userId);
        } else {
            log.error("Фильм не найден");
            throw new FilmNotFoundException("Фильм не найден");
        }
    }

    public void removeLike(int id, int userId) {
        likeStorage.removeLikes(id, userId);
    }

    public List<FilmDto> popularFilms(int count) {
        if (count == 1) {
            log.info("Самый популярный фильм:");
        } else {
            log.info("{} популярных фильмов:", count);
        }
        return filmStorage.popularFilms(count).stream()
                .map(this::convertFilmToDto)
                .collect(Collectors.toList());
    }

    private FilmDto convertFilmToDto(Film film) {

        return FilmDto.builder()
                .id(film.getId())
                .name(film.getName())
                .description(film.getDescription())
                .releaseDate(film.getReleaseDate())
                .duration(film.getDuration())
                .mpa(film.getMpa())
                .likes(film.getLikes())
                .genres(film.getGenres())
                .build();
    }

    private Film convertFilmDtoToFilm(FilmDto filmDto) {

        return Film.builder()
                .id(filmDto.getId())
                .name(filmDto.getName())
                .description(filmDto.getDescription())
                .releaseDate(filmDto.getReleaseDate())
                .duration(filmDto.getDuration())
                .mpa(filmDto.getMpa())
                .likes(filmDto.getLikes())
                .genres(filmDto.getGenres())
                .build();
    }
}