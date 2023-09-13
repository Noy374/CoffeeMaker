package com.example.coffeemaker.validations;

import com.example.coffeemaker.anatations.UniqueUsername;
import com.example.coffeemaker.services.CoffeeHouseService;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,Object>{

    private final CoffeeHouseService coffeeHouseService;
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return coffeeHouseService.checkCoffeeHouseByUsername((String) o);
    }
}
