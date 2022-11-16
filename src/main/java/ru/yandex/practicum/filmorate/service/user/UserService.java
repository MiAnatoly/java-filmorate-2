package ru.yandex.practicum.filmorate.service.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserService {
    User addUser(User newUser);

    User updateUser(User userToUpdate);

    List<User> getAllUsers();

    User getById(int userId);

    void addFriend(int userId, int friendId);

    void removeFriend(int userId, int friendId);

    List<User> getCommonFriends(int userId, int friendId);

    List<User> getFriends(int userId);

    void clear();
}
