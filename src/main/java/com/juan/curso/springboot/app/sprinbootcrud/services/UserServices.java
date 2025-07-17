package com.juan.curso.springboot.app.sprinbootcrud.services;

import com.juan.curso.springboot.app.sprinbootcrud.model.User;

import java.util.List;

public interface UserServices{

    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);
}
