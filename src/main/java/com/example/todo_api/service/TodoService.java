package com.example.todo_api.service;

import com.example.todo_api.dto.TodoCreateRequest;
import com.example.todo_api.dto.TodoResponse;
import com.example.todo_api.dto.TodoUpdateRequest;
import com.example.todo_api.model.Todo;
import com.example.todo_api.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//****************************- Service Layer -***************************************

@Service       //'Service' Annotation helps the TodoController's constructor to get a bean of the TodoService. // Spring creates this bean automatically
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponse> getAllTodos() {
        return todoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<TodoResponse> getTodoById(Long id) {
        return todoRepository.findById(id)
                .map(this::toResponse);
    }

    public TodoResponse createTodo(TodoCreateRequest createRequest) {
        Todo todo = new Todo(createRequest.getTitle(), createRequest.getDescription());
        Todo saved = todoRepository.save(todo);
        return toResponse(saved);
    }


    //    Request -> Entity
    public TodoResponse updateTodo(Long id, TodoUpdateRequest updateRequest) {
        Todo updated =  todoRepository.findById(id)
                .map(todo -> {
                    todo.setTitle(updateRequest.getTitle());
                    todo.setCompleted(updateRequest.isCompleted());
                    todo.setDescription(updateRequest.getDescription());
                    return todoRepository.save(todo);
                })
                .orElseThrow(() -> new RuntimeException("Todo not found: " + id));

        return toResponse(updated);
    }

    public void deleteTodo(Long id){
        if(!todoRepository.existsById(id)){
            throw new RuntimeException("Todo not found: " + id);
        }
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
}
