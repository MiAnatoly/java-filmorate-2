package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.UserDto;
import ru.yandex.practicum.filmorate.service.user.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.findAll();
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping
    public UserDto updateUser(@Valid @RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @GetMapping("/{id}")
    public UserDto findBId(@PathVariable int id) {
        return userService.findById(id);
    }

    @DeleteMapping
    public void removeUser(@Valid @RequestBody UserDto userDto) {
        userService.removeUser(userDto);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriends(@PathVariable int id, @PathVariable int friendId) {
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriends(@PathVariable int id, @PathVariable int friendId) {
        userService.removeFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public List<UserDto> getAllFriends(@PathVariable int id) {
        return userService.getAllFriends(id);
    }

    @GetMapping("/{id}/friends/common/{friendId}")
    public List<UserDto> getCommonFriends(@PathVariable int id, @PathVariable int friendId) {
        return userService.getCommonFriends(id, friendId);
    }
}
