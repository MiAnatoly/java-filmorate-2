package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.dto.FilmDto;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.film.FilmService;
import ru.yandex.practicum.filmorate.storage.mpa.MpaStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(scripts = "classpath:schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FilmServiceTest {
    private final FilmService filmService;
    private final MpaStorage mpaStorage;
    private final UserStorage userStorage;

    @Test
    public void findFilmByIdTest() {
        FilmDto filmDto = FilmDto.builder()
                .name("New film")
                .description("Description")
                .releaseDate(LocalDate.of(2000, 12, 12))
                .duration(90)
                .build();

        filmDto.setMpa(mpaStorage.getMpaById(1));

        filmService.createFilm(filmDto);

        Optional<FilmDto> filmOptional = Optional.ofNullable(filmService.findById(1));

        assertThat(filmOptional)
                .isPresent()
                .hasValueSatisfying(film ->
                        assertThat(film).hasFieldOrPropertyWithValue("id", 1)
                );
    }

    @Test
    public void updateFilmTest() {
        FilmDto film = FilmDto.builder()
                .name("New film")
                .description("Description")
                .releaseDate(LocalDate.of(2000, 12, 12))
                .duration(90)
                .mpa(mpaStorage.getMpaById(1))
                .build();

        film = filmService.createFilm(film);

        FilmDto filmUpdated = FilmDto.builder()
                .id(film.getId())
                .name("Updated film")
                .description("Description")
                .releaseDate(LocalDate.of(2000, 12, 12))
                .mpa(mpaStorage.getMpaById(1))
                .duration(90)
                .build();

        filmService.updateFilm(filmUpdated);

        film = filmService.findById(film.getId());

        assertEquals("Updated film", film.getName());
    }

    @Test
    public void getAllFilmsTest() {
        FilmDto film1 = FilmDto.builder()
                .name("Film 1")
                .description("Description")
                .releaseDate(LocalDate.of(2000, 12, 12))
                .duration(90)
                .mpa(mpaStorage.getMpaById(1))
                .build();

        filmService.createFilm(film1);

        FilmDto film2 = FilmDto.builder()
                .name("Film 2")
                .description("Description")
                .releaseDate(LocalDate.of(2000, 12, 12))
                .duration(90)
                .mpa(mpaStorage.getMpaById(1))
                .build();

        filmService.createFilm(film2);

        assertEquals(2, filmService.findAll().size());
    }

    @Test
    public void removeFilmTest() {
        FilmDto film1 = FilmDto.builder()
                .name("Film 1")
                .description("Description")
                .releaseDate(LocalDate.of(2000, 12, 12))
                .duration(90)
                .mpa(mpaStorage.getMpaById(1))
                .build();

        filmService.createFilm(film1);

        FilmDto film2 = FilmDto.builder()
                .name("Film 2")
                .description("Description")
                .releaseDate(LocalDate.of(2000, 12, 12))
                .duration(90)
                .mpa(mpaStorage.getMpaById(1))
                .build();

        film2 = filmService.createFilm(film2);

        filmService.removeFilm(film2);

        assertEquals(1, filmService.findAll().size());
    }

    @Test
    public void addLikeTest() {
        FilmDto film = FilmDto.builder()
                .name("Film 1")
                .description("Description")
                .releaseDate(LocalDate.of(2000, 12, 12))
                .duration(90)
                .mpa(mpaStorage.getMpaById(1))
                .build();

        film = filmService.createFilm(film);

        User user = User.builder()
                .name("User")
                .login("user")
                .email("123hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user = userStorage.createUser(user);

        filmService.putLike(film.getId(), user.getId());

        assertEquals(1, filmService.popularFilms(10).size());
    }

    @Test
    public void removeLikeTest() {
        FilmDto film1 = FilmDto.builder()
                .name("Film 1")
                .description("Description")
                .releaseDate(LocalDate.of(2000, 12, 12))
                .duration(90)
                .mpa(mpaStorage.getMpaById(1))
                .build();

        film1 = filmService.createFilm(film1);

        FilmDto film2 = FilmDto.builder()
                .name("Film 2")
                .description("Description")
                .releaseDate(LocalDate.of(2000, 12, 12))
                .duration(90)
                .mpa(mpaStorage.getMpaById(1))
                .build();

        film2 = filmService.createFilm(film2);

        User user1 = User.builder()
                .name("User1")
                .login("user1")
                .email("1hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user1 = userStorage.createUser(user1);

        User user2 = User.builder()
                .name("User2")
                .login("user2")
                .email("2hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user2 = userStorage.createUser(user2);

        User user3 = User.builder()
                .name("User3")
                .login("user3")
                .email("3hello@mail.org")
                .birthday(LocalDate.of(1995, 10, 13))
                .build();

        user3 = userStorage.createUser(user3);

        filmService.putLike(film1.getId(), user1.getId());
        filmService.putLike(film1.getId(), user2.getId());
        filmService.putLike(film1.getId(), user3.getId());
        filmService.putLike(film2.getId(), user3.getId());

        filmService.removeLike(film2.getId(), user3.getId());

        assertEquals(2, filmService.popularFilms(10).size());
    }

}
