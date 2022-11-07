package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserRepository;
import ru.yandex.practicum.filmorate.storage.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
    }

    @Test
    public void addUserWithFailId() {
        List<User> users = new ArrayList<>();

        user = User.builder()
                .login("user")
                .name("User")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        userRepository.addUser(user);

        user = User.builder()
                .id(1)
                .login("user")
                .name("User")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        users.add(user);

        assertEquals(users, userRepository.getAllUsers());
    }

    @Test
    public void addUserUserNameWhenUserNameIsNull() {
        List<User> users = new ArrayList<>();

        user = User.builder()
                .login("user")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        userRepository.addUser(user);

        user = User.builder()
                .id(1)
                .login("user")
                .name("user")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        users.add(user);

        assertEquals(users, userRepository.getAllUsers());
    }

    @Test
    public void addUserNameWhenUserNameIsBlank() {
        List<User> users = new ArrayList<>();

        user = User.builder()
                .login("user")
                .name("")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        userRepository.addUser(user);

        user = User.builder()
                .id(1)
                .login("user")
                .name("user")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        users.add(user);

        assertEquals(users, userRepository.getAllUsers());
    }

    @Test
    public void updateUser() {
        List<User> users = new ArrayList<>();

        user = User.builder()
                .id(1)
                .login("user")
                .name("User")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        userRepository.addUser(user);

        user = User.builder()
                .id(1)
                .login("user")
                .name("Updated")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        users.add(user);
        userRepository.updateUser(user);

        assertEquals(users, userRepository.getAllUsers());
    }

    @Test
    public void userUpdateWithTheWrongId() {
        user = User.builder()
                .id(9999)
                .login("user")
                .name("User")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();

        ValidationException validationException = assertThrows(ValidationException.class,
                () -> userRepository.updateUser(user));

        String message = validationException.getMessage();
        assertTrue(message.contains("Пользователь User с id=9999 не найден"));
    }

    @Test
    public void getAllUsers() {
        List<User> users = new ArrayList<>();

        user = User.builder()
                .id(1)
                .login("user1")
                .name("User1")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1996, 8, 20))
                .build();
        userRepository.addUser(user);
        users.add(user);

        user = User.builder()
                .id(2)
                .login("user2")
                .name("User2")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1976, 8, 20))
                .build();
        userRepository.addUser(user);
        users.add(user);

        user = User.builder()
                .id(3)
                .login("user3")
                .name("User3")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .build();
        userRepository.addUser(user);
        users.add(user);

        assertEquals(userRepository.getAllUsers(), users);
    }

    @Test
    public void clear() {
        user = User.builder()
                .id(1)
                .login("user1")
                .name("User1")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1996, 8, 20))
                .build();
        userRepository.addUser(user);

        user = User.builder()
                .id(2)
                .login("user2")
                .name("User2")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1976, 8, 20))
                .build();
        userRepository.addUser(user);

        user = User.builder()
                .id(3)
                .login("user3")
                .name("User3")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .build();
        userRepository.addUser(user);

        userRepository.clear();
        assertEquals(new ArrayList<User>(), userRepository.getAllUsers());
    }
}
