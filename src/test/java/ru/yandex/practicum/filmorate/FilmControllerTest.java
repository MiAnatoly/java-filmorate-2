package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FilmControllerTest {

    private Film film;
    @Autowired
    private FilmController filmController;

    @BeforeEach
    void setUp() {
        filmController = new FilmController();
    }

    @Test
    public void contextLoad() {
        assertThat(filmController).isNotNull();
    }

    @Test
    public void addNewFilm() {
        film = Film.builder()
                .id(1)
                .name("Film name")
                .description("description")
                .releaseDate(LocalDate.of(1967, 3, 25))
                .duration(100)
                .build();

        filmController.addFilm(film);

        assertThat(filmController.getFilms()).containsExactly(film);
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
        filmController.addFilm(film);

        filmController.updateFilm(film);

        assertThat(filmController.getFilms()).containsExactly(film);
    }

    @Test
    public void getAllFilms() {
        List<Film> films = new ArrayList<>();

        film = Film.builder()
                .id(1)
                .name("New film 1")
                .description("description")
                .releaseDate(LocalDate.of(1970, 4, 11))
                .duration(90)
                .build();
        films.add(film);
        filmController.addFilm(film);

        film = Film.builder()
                .id(2)
                .name("New film 2")
                .description("description 111")
                .releaseDate(LocalDate.of(1971, 4, 11))
                .duration(85)
                .build();
        films.add(film);
        filmController.addFilm(film);

        film = Film.builder()
                .id(3)
                .name("New film 3")
                .description("description 111")
                .releaseDate(LocalDate.of(1972, 4, 11))
                .duration(75)
                .build();
        films.add(film);
        filmController.addFilm(film);

        assertThat(filmController.getFilms()).isEqualTo(films);
    }
}
