package ru.yandex.practicum.filmorate.service.user;

import ru.yandex.practicum.filmorate.dto.UserDTO;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserService {
    UserDTO addUser(User newUser);

    UserDTO updateUser(User userToUpdate);

    List<UserDTO> getAllUsers();

    UserDTO getById(int userId);

    void addFriend(int userId, int friendId);

    void removeFriend(int userId, int friendId);

    List<UserDTO> getCommonFriends(int userId, int friendId);

    List<UserDTO> getFriends(int userId);

    void clear();
}
