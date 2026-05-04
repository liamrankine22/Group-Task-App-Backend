package com.liamrankine.taskmanager.datatransfer.requests.task;

import com.liamrankine.taskmanager.enumerations.UpdateType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TaskUpdateRequest {
    @NotNull
    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDate dueDate;

    //Constructors
    public TaskUpdateRequest() {}
    public TaskUpdateRequest(Long id, String title, String description, String status, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }

    //Setters
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    //Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public LocalDate getDueDate() { return dueDate; }
}
