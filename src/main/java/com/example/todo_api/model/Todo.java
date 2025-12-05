package com.example.todo_api.model;


//****************************- ENTITIES -***************************************

import jakarta.persistence.*;

import java.time.LocalDateTime;


// annotate to act as JPA entity.
@Entity
@Table(name = "todos")
public class Todo {

    @Id     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(name = "is_completed")
    private boolean completed;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(length = 500)
    private String description;

    // Relationship: many todos belong to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Default Constructor (required)
    public Todo() {
        this.createdAt = LocalDateTime.now();
        this.completed = false;
    }

    // Constructors for convenience
    public Todo(String title) {
        this();
        this.title = title;
    }


    // Constructor for detailed creation
    public Todo(Long id, String title, boolean completed, LocalDateTime createdAt, String description) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.createdAt = createdAt;
        this.description = description;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
