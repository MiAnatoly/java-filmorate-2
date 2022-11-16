package ru.yandex.practicum.filmorate.storage.user;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.InternalServerErrorException;
import ru.yandex.practicum.filmorate.exception.SearchedObjectNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class InMemoryUserStorage implements UserStorage {
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
            throw new SearchedObjectNotFoundException("Пользователь "
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
    public User getById(int userId) {
        if (users.containsKey(userId)) {
            return users.get(userId);
        } else {
            throw new SearchedObjectNotFoundException("Пользователь "
                    + " с id="
                    + userId
                    + " не найден");
        }
    }

    @Override
    public void addFriend(int userId, int friendId) {
        if (users.containsKey(userId) && users.containsKey(friendId)) {
            User user = users.get(userId);
            User friend = users.get(friendId);
            user.getFriends().add(friendId);
            friend.getFriends().add(userId);
        } else {
            throw new SearchedObjectNotFoundException("Пользователь "
                    + " с id="
                    + userId
                    + " или друг с id="
                    + friendId
                    + " не найден");
        }
    }

    @Override
    public void removeFriend(int userId, int friendId) {
        if (users.containsKey(userId) && users.containsKey(friendId)) {
            User user = users.get(userId);
            User friend = users.get(friendId);
            user.getFriends().remove(friend.getId());
            friend.getFriends().remove(user.getId());
        } else {
            throw new SearchedObjectNotFoundException("Пользователь "
                    + " с id="
                    + userId
                    + " или друг с id="
                    + friendId
                    + " не найден");
        }
    }

    @Override
    public List<User> getCommonFriends(int userId, int friendId) {
        if (users.containsKey(userId) && users.containsKey(friendId)) {
            User user = users.get(userId);
            User friend = users.get(friendId);

            Set<Integer> commonFriendsId = new HashSet<>(user.getFriends());
            commonFriendsId.retainAll(friend.getFriends());

            return commonFriendsId.stream()
                    .map(users::get)
                    .collect(Collectors.toList());
        } else {
            throw new InternalServerErrorException("Пользователь "
                    + " с id="
                    + userId
                    + " или друг с id="
                    + friendId
                    + " не найден");
        }
    }

    @Override
    public List<User> getFriends(int userId) {
        if (users.containsKey(userId)) {
            Set<Integer> user = users.get(userId).getFriends();

            return user.stream()
                    .map(users::get)
                    .collect(Collectors.toList());
        } else {
            throw new SearchedObjectNotFoundException("Пользователь "
                    + " с id="
                    + userId
                    + " не найден");
        }
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
