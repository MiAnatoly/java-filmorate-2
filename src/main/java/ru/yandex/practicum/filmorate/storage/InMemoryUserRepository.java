package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exception.ValidationExceptions;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private final Map<Integer, User> users;
    private Integer id;

    public InMemoryUserRepository() {
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
            throw new ValidationExceptions("Пользователь "
                    + newUser.getName()
                    + " с id="
                    + newUser.getId()
                    + "уже существует");
        }
    }

    @Override
    public User updateUser(User userToUpdate) {
        if (users.containsKey(userToUpdate.getId())) {
            checkUserName(userToUpdate);
            users.put(userToUpdate.getId(), userToUpdate);
            return userToUpdate;
        } else {
            throw new ValidationExceptions("Пользователь "
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

    private void checkUserName(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
    }
}
