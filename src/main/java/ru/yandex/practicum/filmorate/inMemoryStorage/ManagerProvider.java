package ru.yandex.practicum.filmorate.inMemoryStorage;

public class ManagerProvider {
    private ManagerProvider() {
    }

    public static InMemoryFilmRepository getDefaultFilmsManager() {
        return new InMemoryFilmRepository();
    }

    public static InMemoryUserRepository getDefaultUserManager() {
        return new InMemoryUserRepository();
    }
}
