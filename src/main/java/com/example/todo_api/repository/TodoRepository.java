package com.example.todo_api.repository;

import com.example.todo_api.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//****************************- JPA Repository -***************************************

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // Spring Data JPA auto-implements CRUD methods:
    // save(To-do)
    // findById(Long)
    // findAll()
    // deleteById(Long)
    // And more...

    // You can add custom queries if needed (later)
}
