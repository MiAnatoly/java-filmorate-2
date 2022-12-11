package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.dto.FilmDto;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmDtoTest {
    @Test
    public void whenConvertFilmEntityToFilmDtoCorrect(){
        Film film;
        film = Film.builder()
                .id(1)
                .name("Film 1")
                .description("Фильм хороший.")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(120)
                .mpa(new Mpa(1, "G"))
                .likes(new HashSet<>(List.of(1, 5, 7)))
                .genres(new HashSet<>(List.of(new Genre(3, "Остросюжетный блокбастер"))))
                .build();

        FilmDto filmDto;
        filmDto = FilmDto.builder()
                .id(film.getId())
                .name(film.getName())
                .description(film.getDescription())
                .releaseDate(film.getReleaseDate())
                .duration(film.getDuration())
                .mpa(film.getMpa())
                .likes(film.getLikes())
                .genres(film.getGenres())
                .build();

        assertEquals(film.getId(), filmDto.getId());
        assertEquals(film.getName(), filmDto.getName());
        assertEquals(film.getDescription(), filmDto.getDescription());
        assertEquals(film.getReleaseDate(), filmDto.getReleaseDate());
        assertEquals(film.getDuration(), filmDto.getDuration());
        assertEquals(film.getMpa(), filmDto.getMpa());
        assertEquals(film.getLikes(), filmDto.getLikes());
        assertEquals(film.getGenres(), filmDto.getGenres());
    }
}
