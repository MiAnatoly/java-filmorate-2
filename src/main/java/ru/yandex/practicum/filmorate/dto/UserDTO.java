package ru.yandex.practicum.filmorate.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class UserDTO {
    private Integer id; // идентификатор
    private String email; // адрес электронной почты
    private String login; // логин пользователя
    private String name; // имя пользователя
    private LocalDate birthday; // день рождения
    private Set<Integer> friends;
}
