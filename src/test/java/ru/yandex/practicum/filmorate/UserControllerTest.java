package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserControllerTest {
    private User user;
    @Autowired
    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController();
    }

    @Test
    public void contextLoad() {
        assertThat(userController).isNotNull();
    }

    @Test
    public void addNewFilm() {
        user = User.builder()
                .id(1)
                .login("Djohn Do")
                .name("DD")
                .email("mail12@gmail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();

        userController.addUser(user);

        assertThat(userController.getUsers()).containsExactly(user);
    }

    @Test
    public void updateFilm() {
        user = User.builder()
                .id(1)
                .login("Djohn Do")
                .name("DD")
                .email("mail12@gmail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        userController.addUser(user);

        user = User.builder()
                .id(1)
                .login("Updated user")
                .name("D")
                .email("mail1@gmail.org")
                .birthday(LocalDate.of(1950, 8, 20))
                .build();

        userController.updateUser(user);

        assertThat(userController.getUsers()).containsExactly(user);
    }

    @Test
    public void getAllFilms() {
        List<User> users = new ArrayList<>();

        user = User.builder()
                .id(1)
                .login("User 1")
                .name("D1")
                .email("mail1@gmail.org")
                .birthday(LocalDate.of(1945, 4, 11))
                .build();
        users.add(user);
        userController.addUser(user);

        user = User.builder()
                .id(2)
                .login("User 2")
                .name("D2")
                .email("mail2@gmail.org")
                .birthday(LocalDate.of(1990, 4, 11))
                .build();
        users.add(user);
        userController.addUser(user);

        user = User.builder()
                .id(3)
                .login("User 3")
                .name("D3")
                .email("mail3@gmail.org")
                .birthday(LocalDate.of(1947, 9, 15))
                .build();
        users.add(user);
        userController.addUser(user);

        assertThat(userController.getUsers()).isEqualTo(users);
    }
}
