package ru.yandex.practicum.filmorate.inMemoryStorage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface InMemoryUsers {
    User addUser(User newUser);

    User updateUser(User userToUpdate);

    List<User> getAllUsers();

    void updateId();
}
