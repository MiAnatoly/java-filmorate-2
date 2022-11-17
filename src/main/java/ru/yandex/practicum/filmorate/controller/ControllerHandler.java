package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exception.ErrorResponse;
import ru.yandex.practicum.filmorate.exception.InternalServerErrorException;
import ru.yandex.practicum.filmorate.exception.SearchedObjectNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;

@RestControllerAdvice
@Slf4j
public class ControllerHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorResponse badRequestException(ValidationException exception) {
        log.warn("400 Bad Request", exception);
        return new ErrorResponse(exception.getMessage(), "400 Bad Request");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SearchedObjectNotFoundException.class)
    public ErrorResponse notFound(SearchedObjectNotFoundException exception) {
        log.warn("404 Not Found", exception);
        return new ErrorResponse(exception.getMessage(), "404 Not Found");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerErrorException.class)
    public ErrorResponse internalServerError(InternalServerErrorException exception) {
        log.warn("500 Internal Server Error", exception);
        return new ErrorResponse(exception.getMessage(), "500 Internal Server Error");
    }
}
