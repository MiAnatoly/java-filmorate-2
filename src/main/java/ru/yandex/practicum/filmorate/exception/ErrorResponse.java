package ru.yandex.practicum.filmorate.exception;

public class ErrorResponse {
    // название ошибки
    private final String error;
    // подробное описание

    private final String description;

    public ErrorResponse(String error, String description) {
        this.error = error;
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public String getDescription() {
        return description;
    }
}
