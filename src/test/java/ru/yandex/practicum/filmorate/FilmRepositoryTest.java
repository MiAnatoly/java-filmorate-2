package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmRepository;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilmRepositoryTest {
    private FilmRepository filmRepository;
    private Film film;

    @BeforeEach
    void setUp() {
        filmRepository = new InMemoryFilmRepository();
    }

    @Test
    public void addFilmWithFailId() {
        film = Film.builder()
                .name("Film name")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        filmRepository.addFilm(film);

        List<Film> films = new ArrayList<>();
        film = Film.builder()
                .id(1)
                .name("Film name")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        films.add(film);

        assertEquals(films, filmRepository.getAllFilms());
    }

    @Test
    public void addingWithTheSameId() {
        film = Film.builder()
                .id(1)
                .name("Film name")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();

        ValidationException validationException = assertThrows(ValidationException.class,
                () -> {
                    filmRepository.addFilm(film);
                    filmRepository.addFilm(film);
                });

        String message = validationException.getMessage();
        assertTrue(message.contains("Фильм Film name с id=1 уже существует."));
    }

    @Test
    public void updateFilm() {
        film = Film.builder()
                .id(1)
                .name("Film name")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        filmRepository.addFilm(film);

        film = Film.builder()
                .id(1)
                .name("Updated film")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();

        assertEquals(filmRepository.updateFilm(film), film);
    }

    @Test
    public void movieUpdateWithTheWrongId() {
        film = Film.builder()
                .id(9999)
                .name("Updated film")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();

        ValidationException validationException = assertThrows(ValidationException.class,
                () -> filmRepository.updateFilm(film));

        String message = validationException.getMessage();
        assertTrue(message.contains("Фильм Updated film с id=9999 не найден"));
    }

    @Test
    public void getAllFilms() {
        List<Film> films = new ArrayList<>();

        film = Film.builder()
                .id(1)
                .name("Film 1")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        films.add(film);
        filmRepository.addFilm(film);

        film = Film.builder()
                .id(2)
                .name("Film 2")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        films.add(film);
        filmRepository.addFilm(film);

        film = Film.builder()
                .id(3)
                .name("Film 3")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        films.add(film);
        filmRepository.addFilm(film);

        assertEquals(filmRepository.getAllFilms(), films);
    }

    @Test
    public void clear() {
        film = Film.builder()
                .id(1)
                .name("Film 1")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        filmRepository.addFilm(film);

        film = Film.builder()
                .id(2)
                .name("Film 2")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        filmRepository.addFilm(film);

        film = Film.builder()
                .id(3)
                .name("Film 3")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        filmRepository.addFilm(film);

        filmRepository.clear();
        assertEquals(new ArrayList<>(), filmRepository.getAllFilms());
    }
}
