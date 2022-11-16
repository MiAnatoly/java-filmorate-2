package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.InternalServerErrorException;
import ru.yandex.practicum.filmorate.exception.SearchedObjectNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryUserStorageTest {
    private InMemoryUserStorage userStorage;
    private User user;

    @BeforeEach
    void setUp() {
        userStorage = new InMemoryUserStorage();
    }

    @Test
    public void addUserWithFailIdTest() {
        List<User> users = new ArrayList<>();

        user = User.builder()
                .login("user")
                .name("User")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        userStorage.addUser(user);

        user = User.builder()
                .id(1)
                .login("user")
                .name("User")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        users.add(user);

        assertEquals(users, userStorage.getAllUsers());
    }

    @Test
    public void addUserUserNameWhenUserNameIsNullTest() {
        List<User> users = new ArrayList<>();

        user = User.builder()
                .login("user")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        userStorage.addUser(user);

        user = User.builder()
                .id(1)
                .login("user")
                .name("user")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        users.add(user);

        assertEquals(users, userStorage.getAllUsers());
    }

    @Test
    public void addUserNameWhenUserNameIsBlankTest() {
        List<User> users = new ArrayList<>();

        user = User.builder()
                .login("user")
                .name("")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        userStorage.addUser(user);

        user = User.builder()
                .id(1)
                .login("user")
                .name("user")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        users.add(user);

        assertEquals(users, userStorage.getAllUsers());
    }

    @Test
    public void updateUserTest() {
        List<User> users = new ArrayList<>();

        user = User.builder()
                .id(1)
                .login("user")
                .name("User")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        userStorage.addUser(user);

        user = User.builder()
                .id(1)
                .login("user")
                .name("Updated")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();
        users.add(user);
        userStorage.updateUser(user);

        assertEquals(users, userStorage.getAllUsers());
    }

    @Test
    public void userUpdateWithTheWrongIdTest() {
        user = User.builder()
                .id(9999)
                .login("user")
                .name("User")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1946, 8, 20))
                .build();

        SearchedObjectNotFoundException searchedObjectNotFoundException = assertThrows(SearchedObjectNotFoundException.class,
                () -> userStorage.updateUser(user));

        String message = searchedObjectNotFoundException.getMessage();
        assertTrue(message.contains("Пользователь User с id=9999 не найден"));
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = new ArrayList<>();

        user = User.builder()
                .id(1)
                .login("user1")
                .name("User1")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1996, 8, 20))
                .build();
        userStorage.addUser(user);
        users.add(user);

        user = User.builder()
                .id(2)
                .login("user2")
                .name("User2")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1976, 8, 20))
                .build();
        userStorage.addUser(user);
        users.add(user);

        user = User.builder()
                .id(3)
                .login("user3")
                .name("User3")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .build();
        userStorage.addUser(user);
        users.add(user);

        assertEquals(userStorage.getAllUsers(), users);
    }

    @Test
    public void addFriendTest() {
        List<User> users = new ArrayList<>();
        user = User.builder()
                .id(1)
                .login("user1")
                .name("User1")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .friends(new HashSet<>(List.of(2)))
                .build();
        userStorage.addUser(user);
        users.add(user);

        user = User.builder()
                .id(2)
                .login("user2")
                .name("User2")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .friends(new HashSet<>(List.of(1)))
                .build();
        userStorage.addUser(user);
        users.add(user);

        userStorage.addFriend(1, 2);

        assertEquals(users.get(0), userStorage.getById(1));
        assertEquals(users.get(1), userStorage.getById(2));

        SearchedObjectNotFoundException searchedObjectNotFoundException = assertThrows(SearchedObjectNotFoundException.class,
                () -> userStorage.addFriend(9999, 5));

        String message = searchedObjectNotFoundException.getMessage();
        assertTrue(message.contains("Пользователь с id=9999 или друг с id=5 не найден"));
    }

    @Test
    public void removeFriendTest() {
        List<User> users = new ArrayList<>();
        user = User.builder()
                .id(1)
                .login("user1")
                .name("User1")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .friends(new HashSet<>(List.of(2)))
                .build();
        userStorage.addUser(user);
        users.add(user);

        user = User.builder()
                .id(2)
                .login("user2")
                .name("User2")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .friends(new HashSet<>(List.of(1)))
                .build();
        userStorage.addUser(user);
        users.add(user);

        userStorage.removeFriend(1, 2);

        assertEquals(users.get(0), userStorage.getById(1));
        assertEquals(users.get(1), userStorage.getById(2));

        SearchedObjectNotFoundException searchedObjectNotFoundException = assertThrows(SearchedObjectNotFoundException.class,
                () -> userStorage.removeFriend(9999, 5));

        String message = searchedObjectNotFoundException.getMessage();
        assertTrue(message.contains("Пользователь с id=9999 или друг с id=5 не найден"));
    }

    @Test
    public void getCommonFriendsTest() {
        List<User> expectation = new ArrayList<>();
        user = User.builder()
                .id(1)
                .login("user1")
                .name("User1")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .friends(new HashSet<>(List.of(2)))
                .build();
        userStorage.addUser(user);

        user = User.builder()
                .id(2)
                .login("user1")
                .name("User1")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .friends(new HashSet<>(List.of(2)))
                .build();
        userStorage.addUser(user);
        expectation.add(user);

        assertEquals(expectation, userStorage.getCommonFriends(1, 2));

        InternalServerErrorException internalServerErrorException = assertThrows(InternalServerErrorException.class,
                () -> userStorage.getCommonFriends(9999, 5));

        String message = internalServerErrorException.getMessage();
        assertTrue(message.contains("Пользователь с id=9999 или друг с id=5 не найден"));
    }

    @Test
    public void getFriendsTest() {
        List<User> expectation = new ArrayList<>();
        user = User.builder()
                .id(1)
                .login("user1")
                .name("User1")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .friends(new HashSet<>(List.of(2)))
                .build();
        userStorage.addUser(user);

        user = User.builder()
                .id(2)
                .login("user1")
                .name("User1")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .friends(new HashSet<>(List.of(2 ,3)))
                .build();
        userStorage.addUser(user);
        expectation.add(user);

        user = User.builder()
                .id(3)
                .login("user1")
                .name("User1")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .friends(new HashSet<>(List.of(1)))
                .build();
        userStorage.addUser(user);
        expectation.add(user);

        assertEquals(expectation, userStorage.getFriends(2));

        SearchedObjectNotFoundException searchedObjectNotFoundException = assertThrows(SearchedObjectNotFoundException.class,
                () -> userStorage.getFriends(9999));

        String message = searchedObjectNotFoundException.getMessage();
        assertTrue(message.contains("Пользователь с id=9999 не найден"));

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
        userStorage.addUser(user);

        user = User.builder()
                .id(2)
                .login("user2")
                .name("User2")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1976, 8, 20))
                .build();
        userStorage.addUser(user);

        user = User.builder()
                .id(3)
                .login("user3")
                .name("User3")
                .email("mail123@mail.org")
                .birthday(LocalDate.of(1956, 8, 20))
                .build();
        userStorage.addUser(user);

        userStorage.clear();
        assertEquals(new ArrayList<User>(), userStorage.getAllUsers());
    }
}
