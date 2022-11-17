package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.SearchedObjectNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryFilmStorageTest {
    private InMemoryFilmStorage filmStorage;
    private Film film;

    @BeforeEach
    void setUp() {
        filmStorage = new InMemoryFilmStorage();
    }

    @Test
    public void addFilmWithFailId() {
        film = Film.builder()
                .name("Film name")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        filmStorage.addFilm(film);

        List<Film> films = new ArrayList<>();
        film = Film.builder()
                .id(1)
                .name("Film name")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        films.add(film);

        assertEquals(films, filmStorage.getAllFilms());
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
                    filmStorage.addFilm(film);
                    filmStorage.addFilm(film);
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
        filmStorage.addFilm(film);

        film = Film.builder()
                .id(1)
                .name("Updated film")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();

        assertEquals(filmStorage.updateFilm(film), film);
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

        SearchedObjectNotFoundException searchedObjectNotFoundException = assertThrows(
                SearchedObjectNotFoundException.class,
                () -> filmStorage.updateFilm(film));

        String message = searchedObjectNotFoundException.getMessage();
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
        filmStorage.addFilm(film);

        film = Film.builder()
                .id(2)
                .name("Film 2")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        films.add(film);
        filmStorage.addFilm(film);

        film = Film.builder()
                .id(3)
                .name("Film 3")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        films.add(film);
        filmStorage.addFilm(film);

        assertEquals(filmStorage.getAllFilms(), films);
    }

    @Test
    public void getFilmById() {
        film = Film.builder()
                .id(1)
                .name("Film 1")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        filmStorage.addFilm(film);

        SearchedObjectNotFoundException searchedObjectNotFoundException = assertThrows(
                SearchedObjectNotFoundException.class,
                () -> filmStorage.getById(99999));

        String message = searchedObjectNotFoundException.getMessage();
        assertTrue(message.contains("Фильм с filmId=99999 не найден"));

        assertEquals(filmStorage.getById(1), film);

    }

    @Test
    public void addLikeTest() {
        film = Film.builder()
                .id(1)
                .name("Film 1")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        filmStorage.addFilm(film);

        film = Film.builder()
                .id(2)
                .name("Film 1")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .like(new HashSet<>(List.of(2)))
                .build();
        filmStorage.addFilm(film);

        SearchedObjectNotFoundException searchedObjectNotFoundException1 = assertThrows(
                SearchedObjectNotFoundException.class,
                () -> filmStorage.addLike(9999, 1));

        String message = searchedObjectNotFoundException1.getMessage();
        assertTrue(message.contains("Фильм с filmId=9999 не найден или пользователь с userId=1"));

        SearchedObjectNotFoundException searchedObjectNotFoundException2 = assertThrows(
                SearchedObjectNotFoundException.class,
                () -> filmStorage.addLike(1, -1));

        message = searchedObjectNotFoundException2.getMessage();
        assertTrue(message.contains("Фильм с filmId=1 не найден или пользователь с userId=-1"));
    }

    @Test
    public void removeLikeTest() {
        film = Film.builder()
                .id(1)
                .name("Film 1")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        filmStorage.addFilm(film);

        SearchedObjectNotFoundException searchedObjectNotFoundException1 = assertThrows(
                SearchedObjectNotFoundException.class,
                () -> filmStorage.removeLike(9999, 1));

        String message = searchedObjectNotFoundException1.getMessage();
        assertTrue(message.contains("Фильм с filmId=9999 не найден или пользователь с userId=1"));

        SearchedObjectNotFoundException searchedObjectNotFoundException2 = assertThrows(
                SearchedObjectNotFoundException.class,
                () -> filmStorage.removeLike(1, -1));

        message = searchedObjectNotFoundException2.getMessage();
        assertTrue(message.contains("Фильм с filmId=1 не найден или пользователь с userId=-1"));
    }

    @Test
    public void getTopTest() {
        List<Film> films = new ArrayList<>();
        film = Film.builder()
                .id(1)
                .name("Film 1")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .like(new HashSet<>(List.of(2)))
                .build();
        filmStorage.addFilm(film);
        films.add(film);

        film = Film.builder()
                .id(2)
                .name("Film 1")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .like(new HashSet<>(List.of(1, 2, 3, 4)))
                .build();
        filmStorage.addFilm(film);
        films.add(film);

        film = Film.builder()
                .id(3)
                .name("Film 1")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .like(new HashSet<>(List.of(1, 2, 3, 4, 5, 6)))
                .build();
        filmStorage.addFilm(film);
        films.add(film);

        assertEquals(films.get(2), filmStorage.getTop(3).get(0));
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
        filmStorage.addFilm(film);

        film = Film.builder()
                .id(2)
                .name("Film 2")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        filmStorage.addFilm(film);

        film = Film.builder()
                .id(3)
                .name("Film 3")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();
        filmStorage.addFilm(film);

        filmStorage.clear();
        assertEquals(new ArrayList<Film>(), filmStorage.getAllFilms());
    }
}