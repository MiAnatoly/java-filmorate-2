package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    List<User> getUsers();

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(int id);

    User findById(int id);

    void deleteUser(User user);
}
