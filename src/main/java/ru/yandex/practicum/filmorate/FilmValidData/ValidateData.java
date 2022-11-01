package ru.yandex.practicum.filmorate.FilmValidData;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidateDataValidator.class)
@Documented
public @interface ValidateData {
    String message() default "{ValidateData.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
