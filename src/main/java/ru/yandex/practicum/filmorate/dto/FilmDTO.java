package ru.yandex.practicum.filmorate.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class FilmDTO {
    private Integer id; // идентификатор
    private String name; // название фильма
    private String description; // описание
    private LocalDate releaseDate; // дата выхода в прокат
    private int duration; // продолжительность фильма
    private Set<Integer> like;
}
