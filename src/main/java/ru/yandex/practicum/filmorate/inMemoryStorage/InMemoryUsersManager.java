package ru.yandex.practicum.filmorate.inMemoryStorage;

import ru.yandex.practicum.filmorate.exceprions.ValidationExceptions;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUsersManager implements InMemoryUsers{
    private final Map<Integer, User> users;
    private Integer id;

    public InMemoryUsersManager() {
        this.users = new HashMap<>();
        this.id = 0;
    }

    @Override
    public User addUser(User newUser) {
        if (newUser.getId() == null) {
            updateId();
            newUser.setId(id);
        }

        if (newUser.getName() == null || newUser.getName().isBlank()) {
            newUser.setName(newUser.getLogin());
        }

        if (!users.containsKey(newUser.getId()))
        {
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

    @Override
    public void updateId() {
        ++id;
    }
}
