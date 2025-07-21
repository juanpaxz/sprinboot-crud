package com.juan.curso.springboot.app.sprinbootcrud.Validation;

import com.juan.curso.springboot.app.sprinbootcrud.services.UserServices;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistByUsernameValidation implements ConstraintValidator<ExistByUsername, String> {
    @Autowired  // Inyección por setter
    private UserServices userServices;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (userServices == null) {
            return true;
        }
        return !userServices.existsByUsername(username);
    }
}
