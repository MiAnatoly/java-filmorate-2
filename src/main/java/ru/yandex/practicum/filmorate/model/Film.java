package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.filmorate.filmvalidator.ValidateData;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Film {
    @Positive(message = "Идентификатор фильма отрицательный.")
    @Max(value = Integer.MAX_VALUE, message = "Превышено максимальное значение id" + Integer.MAX_VALUE + ".")
    private Integer id; // идентификатор

    @NotBlank(message = "Название фильма пустое.")
    private String name; // название фильма

    @Size(max = 200, message = "Максимальная длинна поля описания 200 символов.")
    private String description; // описание

    @ValidateData(message = "Дата релиза фильма раньше 28 декабря 1895 года.")
    @PastOrPresent(message = "Дата должна быть не позже сегодня.")
    private LocalDate releaseDate; // дата выхода в прокат

    @Positive(message = "Продолжительность фильма должна быть положительной.")
    private int duration; // продолжительность фильма

    private Set<Integer> like = new HashSet<>();
}
