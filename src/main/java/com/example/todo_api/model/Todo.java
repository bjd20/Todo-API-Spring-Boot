package com.example.todo_api.model;


//****************************- ENTITIES/DTO -***************************************

public class Todo {
    private Long id;
    private String title;
    private boolean completed;
    private String createdAt;

    // Default Constructor (required)
    public Todo() {}

    // Constructor for easy creation
    public Todo(Long id, String title, boolean completed, String createdAt) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.createdAt = createdAt;
    }

    // Getters and Setters (Spring uses these for JSON)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
