package ru.yandex.practicum.filmorate.storage.mpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.SearchedObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DbMpaStorage implements MpaStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Mpa getMpaById(Integer id) {
        if (id < 1) {
            log.error("Идентификатор идентификатор возрастного ограничения меньше нуля");
            throw new SearchedObjectNotFoundException(
                    "Идентификатор идентификатор возрастного ограничения меньше нуля"
            );
        }
        String sql =
                "SELECT * " +
                        "FROM MPA_RATINGS " +
                        "WHERE MPA_ID = ?";

        List<Mpa> mpas = jdbcTemplate.query(sql, (rs, rowNum) -> makeMpa(rs), id);

        if (!mpas.isEmpty()) {
            Mpa mpa = new Mpa();
            mpas.forEach(m -> {
                if (m.getId().equals(id)) {
                    mpa.setId(m.getId());
                    mpa.setName(m.getName());
                }
            });
            return mpa;
        } else {
            log.error("Некорректный идентификатор возрастного ограничения");
            throw new SearchedObjectNotFoundException("Некорректный идентификатор возрастного ограничения");
        }
    }

    @Override
    public List<Mpa> getAllMpa() {
        String sql =
                "SELECT * " +
                        "FROM MPA_RATINGS " +
                        "ORDER BY MPA_ID";
        return jdbcTemplate.query(sql, (rs, rowNum) -> makeMpa(rs));
    }

    private Mpa makeMpa(ResultSet rs) throws SQLException {
        return new Mpa(rs.getInt("MPA_ID"), rs.getString("MPA_NAME"));
    }
}
