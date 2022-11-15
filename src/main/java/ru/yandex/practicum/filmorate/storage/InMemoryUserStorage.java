package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUserStorage implements UserRepository {
    private final Map<Integer, User> users;
    private Integer id;

    public InMemoryUserStorage() {
        this.users = new HashMap<>();
        this.id = 0;
    }

    @Override
    public User addUser(User newUser) {
        if (newUser.getId() == null) {
            newUser.setId(++id);
        }

        checkUserName(newUser);

        if (!users.containsKey(newUser.getId())) {
            users.put(newUser.getId(), newUser);
            return newUser;
        } else {
            throw new ValidationException("Пользователь "
                    + newUser.getName()
                    + " с id="
                    + newUser.getId()
                    + " уже существует");
        }
    }

    @Override
    public User updateUser(User userToUpdate) {
        if (users.containsKey(userToUpdate.getId())) {
            checkUserName(userToUpdate);
            users.put(userToUpdate.getId(), userToUpdate);
            return userToUpdate;
        } else {
            throw new ValidationException("Пользователь "
                    + userToUpdate.getName()
                    + " с id="
                    + userToUpdate.getId()
                    + " не найден");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void clear() {
        users.clear();
    }

    private void checkUserName(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
    }
}
