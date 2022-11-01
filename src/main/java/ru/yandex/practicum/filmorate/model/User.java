package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class User {
    @Positive(message = "Идентификатор пользователя отрицательный")
    @Max(value = Integer.MAX_VALUE, message = "Превышено максимальное значение id" + Integer.MAX_VALUE + ".")
    private Integer id; // идентификатор

    @Email
    @NotBlank(message = "Пустой адрес электронной почты")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Не корректный e-mail.")
    private String email; // адрес электронной почты


    @Pattern(regexp = "^(?!\\s*$).+", message = "Некорректный логин пользователя.")
    private String login; // логин пользователя

    private String name; // имя пользователя

    @PastOrPresent
    private LocalDate birthday; // день рождения
}
