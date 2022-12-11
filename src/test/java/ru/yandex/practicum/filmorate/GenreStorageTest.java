package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.genre.GenreStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Sql(scripts = "classpath:schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GenreStorageTest {
    private final GenreStorage genreStorage;

    @Test
    public void findGenreByIdTest() {
        Genre genre = genreStorage.getGenreById(1);

        assertNotNull(genre);

        assertEquals("Комедия", genreStorage.getGenreById(genre.getId()).getName());
    }

    @Test
    public void findAllTest() {
        assertEquals(6, genreStorage.getAllGenres().size());
    }
}
