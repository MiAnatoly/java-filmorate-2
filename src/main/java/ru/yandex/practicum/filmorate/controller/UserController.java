package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.inMemoryStorage.InMemoryUsers;
import ru.yandex.practicum.filmorate.inMemoryStorage.ManagerProvider;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final InMemoryUsers usersManager = ManagerProvider.getDefaultUserManager();
    // добавление пользователя
    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        log.info("Добавление пользователя: " + user.getName());
        return usersManager.addUser(user);
    }

    // обновление пользователя
    @PutMapping
    public User updateUser(@Valid @RequestBody User userToUpdate) {
        log.info("Обновление пользователя " + userToUpdate.getName());
        return usersManager.updateUser(userToUpdate);
    }

    // получение списка всех пользователей
    @GetMapping
    public List<User> getUsers() {
        log.info("Получение списка всех пользователей" + usersManager.getAllUsers());
        return usersManager.getAllUsers();
    }
}
