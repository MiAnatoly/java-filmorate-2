package ru.yandex.practicum.filmorate.service.user;

import ru.yandex.practicum.filmorate.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    UserDto findById(int id);

    void removeUser(UserDto user);

    void addFriend(int id, int friendId);

    void removeFriend(int id, int friendId);

    List<UserDto> getAllFriends(int id);

    List<UserDto> getCommonFriends(int id, int otherId);
}
