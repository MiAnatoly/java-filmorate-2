package ru.yandex.practicum.filmorate.exception;

public class SearchedObjectNotFoundException extends RuntimeException {
    public SearchedObjectNotFoundException(String message) {
        super(message);
    }
}
