package ru.yandex.practicum.filmorate;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.mpa.MpaStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Sql(scripts = "classpath:schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MpaStorageTest {
    private final MpaStorage mpaStorage;

    @Test
    public void findMpaById() {
        Mpa mpa = mpaStorage.getMpaById(1);

        assertNotNull(mpa);

        assertEquals("G", mpaStorage.getMpaById(mpa.getId()).getName());
    }

    @Test
    public void findAllMpa() {
        assertEquals(5, mpaStorage.getAllMpa().size());
    }
}
