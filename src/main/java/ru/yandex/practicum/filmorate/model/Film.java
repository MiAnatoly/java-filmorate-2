package ru.yandex.practicum.filmorate.model;


import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Film {
    private int id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Integer duration;
    private Mpa mpa;
    private Set<Integer> likes = new HashSet<>();
    private Set<Genre> genres = new HashSet<>();
}