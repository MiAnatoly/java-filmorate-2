package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.dto.UserDto;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDtoTest {
    @Test
    public void whenConvertUserEntityToUserDtoCorrect() {
        User user;
        user = User.builder()
                .id(1)
                .login("user1")
                .name("User1")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .friends(new HashSet<>(List.of(2,3)))
                .build();

        UserDto userDto;
        userDto = UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .friends(user.getFriends())
                .build();

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getLogin(), userDto.getLogin());
        assertEquals(user.getName(), userDto.getName());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getBirthday(), userDto.getBirthday());
        assertEquals(user.getFriends(), userDto.getFriends());
    }

}
