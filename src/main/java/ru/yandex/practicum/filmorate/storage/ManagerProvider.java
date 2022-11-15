package ru.yandex.practicum.filmorate.storage;

public class ManagerProvider {
    private ManagerProvider() {
    }

    public static FilmRepository getDefaultFilmsManager() {
        return new InMemoryFilmStorage();
    }

    public static UserRepository getDefaultUserManager() {
        return new InMemoryUserStorage();
    }
}
