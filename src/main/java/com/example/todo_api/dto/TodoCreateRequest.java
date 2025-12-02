package com.example.todo_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


//****************************- DTO for Create To-Do (Abstraction over model/entity)-***************************************
//DTOs separate API contract from internal entities

public class TodoCreateRequest {

    @NotBlank
    @Size(min=3, max=100)
    private String title;

    @Size(max = 500)
    private String description;

    // Getter & Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
