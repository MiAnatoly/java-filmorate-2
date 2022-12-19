package ru.yandex.practicum.filmorate.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dto.UserDto;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.friend.FriendsStorage;
import ru.yandex.practicum.filmorate.storage.like.LikeStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;
    private final FriendsStorage friendsStorage;
    private final LikeStorage likeStorage;

    @Autowired
    public UserServiceImpl(UserStorage userStorage, FriendsStorage friendsStorage, LikeStorage likeStorage) {
        this.userStorage = userStorage;
        this.friendsStorage = friendsStorage;
        this.likeStorage =likeStorage;
    }

    public List<UserDto> findAll() {
        return userStorage.getUsers().stream()
                .map(this::convertUserToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto createUser(UserDto userDto) {
        User user = convertUserDtoToUser(userDto);
        return convertUserToUserDto(userStorage.createUser(user));
    }

    public UserDto updateUser(UserDto userDto) {
        User user = convertUserDtoToUser(userDto);
        return convertUserToUserDto(userStorage.updateUser(user));
    }

    public void deleteUser(int id) {
        likeStorage.removeLikesUser(id);
        friendsStorage.deleteAllFriendsUser(id);
        userStorage.deleteUser(id);
    }

    public UserDto findById(int id) {
        User user = userStorage.findById(id);
        log.info("По id {} найден пользователь {}", id, user.getLogin());
        return convertUserToUserDto(user);
    }

    public void removeUser(UserDto userDto) {
        User user = convertUserDtoToUser(userDto);
        userStorage.deleteUser(user);
    }

    public void addFriend(int id, int friendId) {
        if (!userStorage.getUsers().contains(userStorage.findById(id)) || !userStorage.getUsers().contains(userStorage.findById(friendId))) {
            log.error("Пользователь не найден");
            throw new UserNotFoundException("Пользователь не найден");
        } else {
            friendsStorage.addFriend(id, friendId);
            log.info("Пользователь {} добавил в друзья пользователя {}.", userStorage.findById(id).getLogin(), userStorage.findById(friendId).getLogin());
        }
    }

    public void removeFriend(int id, int friendId) {
        if (friendsStorage.getAllFriendsUser(id).contains(userStorage.findById(friendId))) {
            log.info("Пользователь {} удалил из друзей пользователя {}.", userStorage.findById(id).getLogin(), userStorage.findById(friendId).getLogin());
            friendsStorage.deleteFriend(id, friendId);
        } else {
            log.error("Такого пользователя нет в друзьях");
            throw new UserNotFoundException("Такого пользователя нет в друзьях");
        }
    }

    public List<UserDto> getAllFriends(int id) {
        return friendsStorage.getAllFriendsUser(id).stream()
                .map(this::convertUserToUserDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> getCommonFriends(int id, int otherId) {
        List<User> users = getAllFriends(id).stream()
                .map(this::convertUserDtoToUser)
                .collect(Collectors.toList());

        List<User> otherUser = getAllFriends(otherId).stream()
                .map(this::convertUserDtoToUser)
                .collect(Collectors.toList());

        log.info("Общие друзья у пользователей с id {} и {}:", id, otherId);
        List<User> resault = users.stream()
                .filter(otherUser::contains)
                .collect(Collectors.toList());

        return resault.stream()
                .map(this::convertUserToUserDto)
                .collect(Collectors.toList());
    }

    private UserDto convertUserToUserDto(User user) {

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .login(user.getLogin())
                .name(user.getName())
                .birthday(user.getBirthday())
                .friends(user.getFriends())
                .build();
    }

    private User convertUserDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .login(userDto.getLogin())
                .name(userDto.getName())
                .birthday(userDto.getBirthday())
                .friends(userDto.getFriends())
                .build();
    }
}
