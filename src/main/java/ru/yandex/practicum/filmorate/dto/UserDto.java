package ru.yandex.practicum.filmorate.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserDto {
    private Integer id;

    @Email
    private String email;

    @NotNull(message = "Не может быть null")
    @Pattern(regexp = "^\\S*$")
    private String login;

    @NotNull(message = "Не может быть null")
    private String name;

    @NotNull(message = "Не может быть null")
    private LocalDate birthday;

    private Set<Integer> friends;
}
