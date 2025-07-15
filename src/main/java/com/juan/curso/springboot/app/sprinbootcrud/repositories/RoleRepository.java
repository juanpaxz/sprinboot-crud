package com.juan.curso.springboot.app.sprinbootcrud.repositories;

import com.juan.curso.springboot.app.sprinbootcrud.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
