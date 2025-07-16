package com.juan.curso.springboot.app.sprinbootcrud.services;

import com.juan.curso.springboot.app.sprinbootcrud.model.Role;
import com.juan.curso.springboot.app.sprinbootcrud.model.User;
import com.juan.curso.springboot.app.sprinbootcrud.repositories.RoleRepository;
import com.juan.curso.springboot.app.sprinbootcrud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {
        // 1. Buscar el rol "ROLE_USER" en la base de datos
        Optional<Role> optionalRole = roleRepository.findByName("ROLE_USER");

        // 2. Crear una lista vacía de roles
        List<Role> roles = new ArrayList<>();

        // 3. Si existe el rol "ROLE_USER", añadirlo a la lista
        optionalRole.ifPresent(roles::add);

        // 4. Si el usuario es administrador (campo booleano isIsadmin == true)
        if (user.isIsadmin()) {
            // 4.1 Buscar también el rol "ROLE_ADMIN"
            Optional<Role> optionalAdminRole = roleRepository.findByName("ROLE_ADMIN");

            // 4.2 Si existe, añadirlo a la lista de roles
            optionalAdminRole.ifPresent(roles::add);
        }

        // 5. Asignar la lista de roles al usuario
        user.setRole(roles);

        // 6. Encriptar la contraseña del usuario antes de guardar
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 7. Guardar el usuario en la base de datos y devolver el resultado
        return userRepository.save(user);
    }

}
