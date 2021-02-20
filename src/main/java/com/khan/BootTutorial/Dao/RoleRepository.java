package com.khan.BootTutorial.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khan.BootTutorial.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
public Role findByRole(String role);
}
