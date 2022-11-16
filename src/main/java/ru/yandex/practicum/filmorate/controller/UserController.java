package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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
    public User addUser(@Valid @RequestBody User user) {
        log.info("Добавление пользователя: " + user.getName());
        return userService.addUser(user);
    }

    // обновление пользователя
    @PutMapping
    public User updateUser(@Valid @RequestBody User userToUpdate) {
        log.info("Обновление пользователя " + userToUpdate.getName());
        return userService.updateUser(userToUpdate);
    }

    // получение списка всех пользователей
    @GetMapping
    public List<User> getUsers() {
        log.info("Получение списка всех пользователей" + userService.getAllUsers());
        return userService.getAllUsers();
    }

    // запрос пользователя по id
    @GetMapping("/{userId}")
    public User getUserById(@Valid @PathVariable int userId) {
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
    public List<User> getFriends(@Valid @PathVariable int userId) {
        log.info("Получение списка друзей пользователя с userId=" + userId);
        return userService.getFriends(userId);
    }

    // запрос списка общих друзей
    @GetMapping("/{userId}/friends/common/{friendId}")
    public List<User> getCommonFriends(@Valid @PathVariable int userId, @PathVariable int friendId) {
        log.info("Запрос общих друзей userId=" + userId + " и friendId=" + friendId);
        return userService.getCommonFriends(userId, friendId);
    }
}