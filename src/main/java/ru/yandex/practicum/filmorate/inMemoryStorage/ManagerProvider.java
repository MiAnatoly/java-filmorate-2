package ru.yandex.practicum.filmorate.inMemoryStorage;

public class ManagerProvider {
    private ManagerProvider() {
    }

    public static inMemoryFilmsManager getDefaultFilmsManager() {
        return new inMemoryFilmsManager();
    }

    public static InMemoryUsersManager getDefaultUserManager() {
        return new InMemoryUsersManager();
    }
}
