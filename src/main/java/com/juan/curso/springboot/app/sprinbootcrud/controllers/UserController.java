package com.juan.curso.springboot.app.sprinbootcrud.controllers;

import com.juan.curso.springboot.app.sprinbootcrud.model.User;
import com.juan.curso.springboot.app.sprinbootcrud.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
    @RequestMapping("/api/users")
public class UserController {

    private UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userServices.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return validation(bindingResult);
        }
        User savedUser = userServices.save(user);
        return ResponseEntity.status(201).body(savedUser);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user,BindingResult bindingResult) {
        user.setIsadmin(false);
        return create(user, bindingResult);
    }

    private ResponseEntity<?> validation(BindingResult bindingResult) {
        Map<String, String> errors = bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage()
                ));
        return ResponseEntity.badRequest().body(errors);
    }

}
