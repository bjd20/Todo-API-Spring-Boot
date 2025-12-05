package com.example.todo_api.repository;

import com.example.todo_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//  custom queries (Derived Query methods):
//  JpaRepository parses the method name : find + By + Username.

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
