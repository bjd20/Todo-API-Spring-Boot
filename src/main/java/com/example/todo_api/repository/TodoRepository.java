package com.example.todo_api.repository;

import com.example.todo_api.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


//****************************- In Memory Repository -***************************************

@Repository
public class TodoRepository {
    private static List<Todo> todos = new ArrayList<>();
    private static AtomicLong idCounter = new AtomicLong(1);

    static {
        // sample data
        todos.add(new Todo(idCounter.getAndIncrement(), "Learn Spring Boot", false, "2025-11-29"));
        todos.add(new Todo(idCounter.getAndIncrement(), "Build Todo API", true, "2025-11-29"));
    }

    public List<Todo> findAll() {
        return new ArrayList<>(todos);
    }

    public Optional<Todo> findById(Long id){
        return todos.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public Todo save(Todo todo){
        if(todo.getId() == null){
            todo.setId(idCounter.getAndIncrement());
            todo.setCreatedAt("2025-11-30");
        }

        todos.removeIf(t -> t.getId().equals(todo.getId()));
        todos.add(todo);
        return todo;
    }

    public boolean deleteById(Long id){
        return todos.removeIf(t -> t.getId().equals(id));
    }
}
