package com.example.todo_api.controller;

import com.example.todo_api.dto.TodoCreateRequest;
import com.example.todo_api.dto.TodoResponse;
import com.example.todo_api.dto.TodoUpdateRequest;
import com.example.todo_api.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//****************************- Real To-do API -***************************************


@RestController
@RequestMapping("/api/todos")           // Base path for all to-do endpoints
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoResponse> getAllTodos(){
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodoById(@PathVariable Long id){
        return todoService.getTodoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TodoResponse createTodo(@Valid @RequestBody TodoCreateRequest todoCreateRequest){
        return todoService.createTodo(todoCreateRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long id, @Valid @RequestBody TodoUpdateRequest todoUpdateRequest){
        return ResponseEntity.ok(todoService.updateTodo(id, todoUpdateRequest));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if (todoService.deleteTodo(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
