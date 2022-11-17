package ru.yandex.practicum.filmorate.service.film;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dto.FilmDTO;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmServiceImplementation implements FilmService {
    private final FilmStorage filmStorage;

    @Autowired
    public FilmServiceImplementation(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    @Override
    public FilmDTO addFilm(Film film) {
        return convertEntityToDto(filmStorage.addFilm(film));
    }

    @Override
    public FilmDTO updateFilm(Film filmToUpdate) {
        return convertEntityToDto(filmStorage.updateFilm(filmToUpdate));
    }

    @Override
    public List<FilmDTO> getAllFilms() {
        return filmStorage.getAllFilms()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FilmDTO getById(int filmId) {
        return convertEntityToDto(filmStorage.getById(filmId));
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
    public List<FilmDTO> getTop(int count) {
        return filmStorage.getTop(count)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        filmStorage.clear();
    }

    private FilmDTO convertEntityToDto(Film film) {
        FilmDTO filmDTO;
        filmDTO = FilmDTO.builder()
                .id(film.getId())
                .name(film.getName())
                .description(film.getDescription())
                .releaseDate(film.getReleaseDate())
                .duration(film.getDuration())
                .like(film.getLike())
                .build();
        return filmDTO;
    }
}
