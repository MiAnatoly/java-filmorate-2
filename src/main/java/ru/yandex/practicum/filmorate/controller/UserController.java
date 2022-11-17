package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.UserDTO;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.user.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // добавление пользователя
    @PostMapping
    public UserDTO addUser(@Valid @RequestBody User user) {
        log.info("Добавление пользователя: " + user.getName());
        return userService.addUser(user);
    }

    // обновление пользователя
    @PutMapping
    public UserDTO updateUser(@Valid @RequestBody User userToUpdate) {
        log.info("Обновление пользователя " + userToUpdate.getName());
        return userService.updateUser(userToUpdate);
    }

    // получение списка всех пользователей
    @GetMapping
    public List<UserDTO> getUsers() {
        log.info("Получение списка всех пользователей" + userService.getAllUsers());
        return userService.getAllUsers();
    }

    // запрос пользователя по id
    @GetMapping("/{userId}")
    public UserDTO getUserById(@Valid @PathVariable int userId) {
        log.info("запрос пользователя по id=" + userId);
        return userService.getById(userId);
    }

    // добавление в друзья
    @PutMapping("/{userId}/friends/{friendId}")
    public void addFriend(@Valid @PathVariable int userId, @PathVariable int friendId) {
        log.info("Добавление пользователей в друзья userId=" + userId + " и friendId=" + friendId);
        userService.addFriend(userId, friendId);
    }

    // удаление из друзей
    @DeleteMapping("/{userId}/friends/{friendId}")
    public void removeFriend(@Valid @PathVariable int userId, @PathVariable int friendId) {
        log.info("Удаление пользователей из друзей userId=" + userId + " и friendId=" + friendId);
        userService.removeFriend(userId, friendId);
    }

    // запрос списка друзей
    @GetMapping("/{userId}/friends")
    public List<UserDTO> getFriends(@Valid @PathVariable int userId) {
        log.info("Получение списка друзей пользователя с userId=" + userId);
        return userService.getFriends(userId);
    }

    // запрос списка общих друзей
    @GetMapping("/{userId}/friends/common/{friendId}")
    public List<UserDTO> getCommonFriends(@Valid @PathVariable int userId, @PathVariable int friendId) {
        log.info("Запрос общих друзей userId=" + userId + " и friendId=" + friendId);
        return userService.getCommonFriends(userId, friendId);
    }
}