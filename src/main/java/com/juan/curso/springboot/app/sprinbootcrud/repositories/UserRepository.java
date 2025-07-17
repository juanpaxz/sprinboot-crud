package com.juan.curso.springboot.app.sprinbootcrud.repositories;

import com.juan.curso.springboot.app.sprinbootcrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}
