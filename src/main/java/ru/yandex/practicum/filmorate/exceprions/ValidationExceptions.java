package ru.yandex.practicum.filmorate.exceprions;

public class ValidationExceptions extends RuntimeException{
    public ValidationExceptions(String message) {
        super(message);
    }
}
