package com.example.coffeemaker.anatations;

import com.example.coffeemaker.validations.UniqueUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Documented
public @interface UniqueUsername {
    String message() default "Username must be unique";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};

}