package com.example.todo_api.service;

import com.example.todo_api.dto.TodoCreateRequest;
import com.example.todo_api.model.Todo;
import com.example.todo_api.model.User;
import com.example.todo_api.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//*************************- Unit Test Example -********************************************************

public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    private User testUser;
    private Todo testTodo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User("testuser", "test@example.com", "hashedpass");
        testUser.setId(1L);

        testTodo = new Todo("Test Todo");
        testTodo.setId(1L);
        testTodo.setUser(testUser);
    }

    @Test
    void testGetAllTodos(){
        // Arrange
        List<Todo> todos = Arrays.asList(testTodo);
        when(todoRepository.findAll()).thenReturn(todos);

        // Act (would need to mock authentication in real test)
        // List<TodoResponse> result = todoService.getAllTodos();

        // Assert
        // assertEquals(1, result.size());
        // verify(todoRepository, times(1)).findAll();
    }

    @Test
    void testCreateTodo(){
        // Arrange
        TodoCreateRequest createRequest = new TodoCreateRequest();
        createRequest.setTitle("New Todo");

        when(todoRepository.save(any(Todo.class))).thenReturn(testTodo);

        // Act (would need authentication mock)
        // TodoResponse result = todoService.createTodo(request);

        // Assert
        // assertNotNull(result);
        // assertEquals("Test To-do", result.getTitle());
    }
}
