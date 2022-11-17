package ru.yandex.practicum.filmorate.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dto.UserDTO;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserServiceImplementation(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public UserDTO addUser(User newUser) {
        return convertEntityToDTO(userStorage.addUser(newUser));
    }

    @Override
    public UserDTO updateUser(User userToUpdate) {
        return convertEntityToDTO(userStorage.updateUser(userToUpdate));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userStorage.getAllUsers()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getById(int userId) {
        return convertEntityToDTO(userStorage.getById(userId));
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
    public List<UserDTO> getCommonFriends(int userId, int friendId) {
        return userStorage.getCommonFriends(userId, friendId)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getFriends(int userId) {
        return userStorage.getFriends(userId)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        userStorage.clear();
    }

    private UserDTO convertEntityToDTO(User user) {
        UserDTO userDTO;
        userDTO = UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .login(user.getLogin())
                .name(user.getName())
                .birthday(user.getBirthday())
                .friends(user.getFriends())
                .build();
        return userDTO;
    }
}
