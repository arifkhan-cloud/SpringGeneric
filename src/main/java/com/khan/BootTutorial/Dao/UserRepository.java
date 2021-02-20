package com.khan.BootTutorial.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khan.BootTutorial.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
public User findByEmail(String email);
}
