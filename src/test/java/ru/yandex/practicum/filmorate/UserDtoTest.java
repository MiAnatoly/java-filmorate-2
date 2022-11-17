package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.dto.UserDTO;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDtoTest {
    @Test
    public void whenConvertUserEntityToUserDTOCorrect() {
        User user;
        user = User.builder()
                .id(2)
                .login("user1")
                .name("User1")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .friends(new HashSet<>(List.of(2, 3)))
                .build();

        UserDTO userDTO;
        userDTO = UserDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .friends(user.getFriends())
                .build();

        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getLogin(), userDTO.getLogin());
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getBirthday(), userDTO.getBirthday());
        assertEquals(user.getFriends(), userDTO.getFriends());
    }
}
