package com.example.todo_api.service;

import com.example.todo_api.dto.TodoCreateRequest;
import com.example.todo_api.dto.TodoResponse;
import com.example.todo_api.dto.TodoUpdateRequest;
import com.example.todo_api.model.Todo;
import com.example.todo_api.model.User;
import com.example.todo_api.repository.TodoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

//****************************- Service Layer -***************************************

@Service       //'Service' Annotation helps the TodoController's constructor to get a bean of the TodoService. // Spring creates this bean automatically
public class TodoService {

    private final TodoRepository todoRepository;
    private final AuthService authService;


    public TodoService(TodoRepository todoRepository, AuthService authService) {
        this.todoRepository = todoRepository;
        this.authService = authService;
    }

    // getTodos filtered by user
    public List<TodoResponse> getAllTodos() {
        User currrentUser = getCurrentUser();

        return todoRepository.findAll()
                .stream()
                .filter(t -> t.getUser().getId().equals(currrentUser.getId()))
                .map(this::toResponse)
                .toList();
    }

    public TodoResponse getTodoById(Long id) {
        User currentUser = getCurrentUser();

        Todo todo =  todoRepository.findById(id)
                .filter(t -> t.getUser().getId().equals(currentUser.getId()))
                .orElseThrow(() -> new RuntimeException("Invalid todo_id for the user_" + currentUser.getId()));

        return toResponse(todo);
    }


    public TodoResponse createTodo(TodoCreateRequest createRequest) {
        User currrentUser = getCurrentUser();

        Todo todo = new Todo(createRequest.getTitle());
        todo.setDescription(createRequest.getDescription());
        todo.setUser(currrentUser);

        Todo saved = todoRepository.save(todo);
        return toResponse(saved);
    }


    //    Request -> Entity
    public TodoResponse updateTodo(Long id, TodoUpdateRequest updateRequest) {
        User currentUser = getCurrentUser();

        Todo updated =  todoRepository.findById(id)
                .filter(t -> t.getUser().getId().equals(currentUser.getId()))
                .map(todo -> {
                    todo.setTitle(updateRequest.getTitle());
                    todo.setCompleted(updateRequest.isCompleted());
                    todo.setDescription(updateRequest.getDescription());
                    return todoRepository.save(todo);
                })
                .orElseThrow(() -> new RuntimeException("Todo not found: " + id));

        return toResponse(updated);
    }

    // Delete Todos by id and filtered by user.
    public void deleteTodo(Long id){

        User currentUser = getCurrentUser();

        Todo todo = todoRepository.findById(id)
                .filter(t -> t.getUser().getId().equals(currentUser.getId()))
                .orElseThrow(() -> new RuntimeException("Invalid todo_id"));

        todoRepository.deleteById(id);
    }

//    Simple Mapper Methods for Model -> DTO response

    //    Entity -> Response
    private TodoResponse toResponse(Todo todo) {
        TodoResponse res = new TodoResponse();
        res.setId(todo.getId());
        res.setTitle(todo.getTitle());
        res.setCompleted(todo.isCompleted());
        res.setCreatedAt(todo.getCreatedAt().toString());
        res.setDescription(todo.getDescription());
        return res;
    }


    private List<TodoResponse> toResponseList(List<Todo> todos) {
        return todos.stream()
                .map(this::toResponse)
                .toList();
    }


    // Helper to get current user
    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            return (String) auth.getPrincipal();
        }
        throw new RuntimeException("User not authenticated");
    }

    private User getCurrentUser() {
        return authService.findByUsername(getCurrentUsername());
    }
}
