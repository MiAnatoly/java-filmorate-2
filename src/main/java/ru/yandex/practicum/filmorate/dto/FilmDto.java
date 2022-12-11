package ru.yandex.practicum.filmorate.dto;

import lombok.*;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {
    private int id;

    @NotNull(message = "Не может быть null")
    private String name;

    @NotNull(message = "Не может быть null")
    private String description;

    @NotNull(message = "Не может быть null")
    private LocalDate releaseDate;

    @NotNull
    private Integer duration;

    private Mpa mpa;
    private Set<Integer> likes;
    private Set<Genre> genres;
}
