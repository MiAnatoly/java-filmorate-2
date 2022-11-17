package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Positive(message = "Идентификатор пользователя отрицательный")
    @Max(value = Integer.MAX_VALUE, message = "Превышено максимальное значение id" + Integer.MAX_VALUE + ".")
    private Integer id; // идентификатор

    @Email
    @NotBlank(message = "Пустой адрес электронной почты")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Не корректный e-mail.")
    private String email; // адрес электронной почты

    @NotBlank(message = "Поле login пустое.")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_.]{1,20}$", message = "Некорректный логин пользователя.")
    private String login; // логин пользователя

    private String name; // имя пользователя

    @PastOrPresent
    private LocalDate birthday; // день рождения

    private Set<Integer> friends = new HashSet<>();
}
