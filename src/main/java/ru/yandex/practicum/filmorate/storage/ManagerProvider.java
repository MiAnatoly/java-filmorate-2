package ru.yandex.practicum.filmorate.storage;

public class ManagerProvider {
    private ManagerProvider() {
    }

    public static FilmRepository getDefaultFilmsManager() {
        return new InMemoryFilmRepository();
    }

    public static UserRepository getDefaultUserManager() {
        return new InMemoryUserRepository();
    }
}
