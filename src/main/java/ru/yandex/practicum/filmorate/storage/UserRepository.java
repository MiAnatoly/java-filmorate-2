package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserRepository {
    User addUser(User newUser);

    User updateUser(User userToUpdate);

    List<User> getAllUsers();
}
