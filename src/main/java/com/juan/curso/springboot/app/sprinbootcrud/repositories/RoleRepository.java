package com.juan.curso.springboot.app.sprinbootcrud.repositories;

import com.juan.curso.springboot.app.sprinbootcrud.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
