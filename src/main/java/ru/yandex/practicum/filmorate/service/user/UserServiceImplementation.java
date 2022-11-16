package ru.yandex.practicum.filmorate.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserServiceImplementation(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public User addUser(User newUser) {
        return userStorage.addUser(newUser);
    }

    @Override
    public User updateUser(User userToUpdate) {
        return userStorage.updateUser(userToUpdate);
    }

    @Override
    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    @Override
    public User getById(int userId) {
        return userStorage.getById(userId);
    }

    @Override
    public void addFriend(int userId, int friendId) {
        userStorage.addFriend(userId, friendId);
    }

    @Override
    public void removeFriend(int userId, int friendId) {
        userStorage.removeFriend(userId, friendId);
    }

    @Override
    public List<User> getCommonFriends(int userId, int friendId) {
        return userStorage.getCommonFriends(userId, friendId);
    }

    @Override
    public List<User> getFriends(int userId) {
        return userStorage.getFriends(userId);
    }

    @Override
    public void clear() {
        userStorage.clear();
    }
}
