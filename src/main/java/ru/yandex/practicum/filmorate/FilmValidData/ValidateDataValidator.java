package ru.yandex.practicum.filmorate.FilmValidData;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ValidateDataValidator implements ConstraintValidator<ValidateData, LocalDate> {
    @Override
    public boolean isValid(LocalDate filmRelease, ConstraintValidatorContext constraintValidatorContext) {
        return !filmRelease.isBefore(LocalDate.of(1895, 12, 8));
    }
}
