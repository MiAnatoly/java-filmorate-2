package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.dto.FilmDTO;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmDTOTest {
    @Test
    public void whenConvertFilmEntityToFilmDTOCorrect() {
        Film film;
        film = Film.builder()
                .id(3)
                .name("Film 1")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .like(new HashSet<>(List.of(1, 2, 3, 4, 5, 6)))
                .build();

        FilmDTO filmDTO;
        filmDTO = FilmDTO.builder()
                .id(3)
                .name("Film 1")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .like(new HashSet<>(List.of(1, 2, 3, 4, 5, 6)))
                .build();

        assertEquals(film.getId(), filmDTO.getId());
        assertEquals(film.getName(), filmDTO.getName());
        assertEquals(film.getDescription(), filmDTO.getDescription());
        assertEquals(film.getReleaseDate(), filmDTO.getReleaseDate());
        assertEquals(film.getDuration(), filmDTO.getDuration());
        assertEquals(film.getLike(), filmDTO.getLike());
    }
}
