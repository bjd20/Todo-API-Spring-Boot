package com.example.todo_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


//****************************- DTO for Create To-Do (Abstraction over model/entity)-***************************************

public class TodoCreateRequest {

    @NotBlank
    @Size(min=3, max=100)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
