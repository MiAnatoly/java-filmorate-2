package ru.yandex.practicum.filmorate.exceprion;

public class ValidationExceptions extends RuntimeException {
    public ValidationExceptions(String message) {
        super(message);
    }
}
